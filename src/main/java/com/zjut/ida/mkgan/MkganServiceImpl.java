package com.zjut.ida.mkgan;

import com.alibaba.fastjson.JSONObject;
import com.zjut.ida.dao.ScholarDao;
import com.zjut.ida.entity.Scholar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
 * @author Casterx on 2022/4/16.
 */
@Service
public class MkganServiceImpl {

    private String requestUrl = "http://10.12.45.49:8508/v1/models/mkgan:predict";
    private String signatureName="serving_default";
    private final Integer ITEM_NUM = 100;



    @Autowired
    private RedisTemplate redisTemplate;

    public List<Long> requestMkganRecommendList(Integer topN,String studentId){
        RestTemplate restTemplate = new RestTemplate();
        // 请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 请求体
        JSONObject requestBody = new JSONObject();
        requestBody.put("signature_name", signatureName);
        String studentRemapId=getStudentRemapId(studentId);
        List<Integer> user_inputs=Arrays.asList(Integer.parseInt(studentRemapId));
        List<Integer> list=Stream.iterate(0, item -> item + 1).limit(ITEM_NUM).collect(Collectors.toList());
        List<List<Integer>> item_inputs=new ArrayList<>(new ArrayList<>());
        for(Integer l:list){
            item_inputs.add(Arrays.asList(l));
        }

        MkganRequest mkganRequest = new MkganRequest();
        mkganRequest.setItem_inputs(item_inputs);
        mkganRequest.setUser_inputs(user_inputs);
        requestBody.put("inputs", mkganRequest);

        System.out.println(requestBody);
        // 发请求
        HttpEntity<JSONObject> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(requestUrl, request, String.class);


        List<BigDecimal> scoredList=new ArrayList<>();
        JSONObject.toJavaObject(JSONObject.parseObject(response.getBody()), MkganResults.class)
                .getOutputs()
                .forEach(lb->scoredList.add(lb.get(0)));
        System.out.println(scoredList);

        // 推荐列表
        return topN(scoredList, topN);


    }


    private List<Long> topN(List<BigDecimal> scoredList, int topN) {
        Map<BigDecimal, Integer> sortedMap = new TreeMap<>(Collections.reverseOrder());
        for (int i = 0; i < scoredList.size(); i++) {
            BigDecimal value = scoredList.get(i);
            sortedMap.put(value, i);
        }


        // 获取 topN
        List<Long> recommendList = new ArrayList<>();
        Iterator<Map.Entry<BigDecimal, Integer>> iter = sortedMap.entrySet().iterator();
        while (iter.hasNext() && recommendList.size() <= topN) {
            Integer itemIndex = iter.next().getValue();
            recommendList.add(getTutorRemapId(itemIndex.toString()));
        }
        System.out.println("\t recommendList="+recommendList);

        return recommendList;
    }



    /**
     * 获取学生重映射 id
     *
     * @return 学生重映射 id
     */
    private String getStudentRemapId(String studentID) {
        return String.valueOf(redisTemplate.opsForValue().get(studentID));
    }

    private Long getTutorRemapId(String tutorID) {
        return Long.parseLong(redisTemplate.opsForValue().get(tutorID).toString());
    }

}
