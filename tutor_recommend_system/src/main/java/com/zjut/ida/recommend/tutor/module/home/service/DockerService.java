package com.zjut.ida.recommend.tutor.module.home.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjut.ida.recommend.tutor.config.SysStudentHolder;
import com.zjut.ida.recommend.tutor.core.constants.KgatModelConstants;
import com.zjut.ida.recommend.tutor.core.exception.BusinessException;
import com.zjut.ida.recommend.tutor.dao.SysCFMapper;
import com.zjut.ida.recommend.tutor.dao.SysStudentMapper;
import com.zjut.ida.recommend.tutor.module.home.dto.KgatModelRequest;
import com.zjut.ida.recommend.tutor.utils.enums.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wly
 * @date 2021/4/22 19:39
 */
@Service
public class DockerService {
    @Autowired
    private KgatModelConstants kgatModelConstants;
    @Autowired
    private SysStudentHolder studentHolder;
    @Autowired
    private SysCFMapper cfMapper;
    @Autowired
    private SysStudentMapper studentMapper;

    private static final Integer ITEM_NUM = 632;

    /**
     * 向 docker 容器发起请求
     *
     * @param topN top N个推荐结果
     * @return 导师重映射 id
     */
    public List<Long> requestKgatRecommendList(Integer topN) {
        RestTemplate restTemplate = new RestTemplate();
        String requestUrl = kgatModelConstants.getRequestUrl();
        // 请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 请求体
        JSONObject requestBody = new JSONObject();
        requestBody.put("signature_name", kgatModelConstants.getSignatureName());
        requestBody.put("inputs", KgatModelRequest.builder()
                .userId(Collections.singletonList(getStudentRemapId()))
                .itemRange(Stream.iterate(0, item -> item + 1).limit(ITEM_NUM).collect(Collectors.toList()))
                .messDropout(Arrays.stream(new Integer[]{0, 0, 0}).collect(Collectors.toList()))
                .build());
        // 发请求
        HttpEntity<JSONObject> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(requestUrl, request, String.class);
        if (!response.hasBody()) {
            throw new BusinessException(ResponseCode.MODEL_ERROR);
        }
        // 排序
        JSONArray responseBody = JSONObject.parseObject(response.getBody())
                .getJSONArray("outputs")
                .getJSONArray(0);
        List<BigDecimal> scoredList = Arrays.stream(responseBody.toArray())
                .map(ele -> new BigDecimal(ele.toString()))
                .collect(Collectors.toList());
        // 推荐列表
        return topN(scoredList, topN);
    }

    /**
     * 获取 top N 个结果
     *
     * @param scoredList 打分列表
     * @param topN 设置 top N
     * @return 导师重映射 id
     */
    private List<Long> topN(List<BigDecimal> scoredList, int topN) {
        Map<BigDecimal, Integer> sortedMap = new TreeMap<>(Collections.reverseOrder());
        for (int i = 0; i < scoredList.size(); i++) {
            BigDecimal value = scoredList.get(i);
            sortedMap.put(value, i);
        }

        // 获取训练集
        List<Integer> trainingItemList = getTrainItems();

        // 获取 topN
        List<Long> recommendList = new ArrayList<>();
        Iterator<Map.Entry<BigDecimal, Integer>> iter = sortedMap.entrySet().iterator();
        while (iter.hasNext() && recommendList.size() <= topN) {
            Integer itemIndex = iter.next().getValue();
            if (!trainingItemList.contains(itemIndex)) {
                recommendList.add(itemIndex.longValue());
            }
        }

        return recommendList;
    }

    /**
     * 获取训练集
     *
     * @return 训练集导师重映射 id
     */
    private List<Integer> getTrainItems() {
        String studentId = studentHolder.getStudent().getStudentId();
        return cfMapper.findTutorRemapIdListByStudentId(studentId);
    }

    /**
     * 获取学生重映射 id
     *
     * @return 学生重映射 id
     */
    private Long getStudentRemapId() {
        String studentId = studentHolder.getStudent().getStudentId();
        return studentMapper.findStudentRemapIdByStudentId(studentId);
    }
}
