package com.zjut.ida.service;

import com.alibaba.fastjson.JSONObject;
import com.zjut.ida.mkgan.MkganRequest;
import com.zjut.ida.mkgan.MkganResults;
import com.zjut.ida.service.impl.HorizontalProjectServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Casterx on 2022/5/30.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CKANTest {


    @Autowired
    private RedisTemplate redisTemplate;
    private final Integer ITEM_NUM = 100;


    private String requestUrl = "http://47.115.216.121:3000/predictions/CKAN";

    @Test
    public void test() {
        RestTemplate restTemplate = new RestTemplate();
        // 请求头
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 请求体
//        JSONObject requestBody = new JSONObject();
//        List<Integer> user_inputs= Arrays.asList(Integer.parseInt(studentRemapId));
//        List<Integer> list= Stream.iterate(0, item -> item + 1).limit(ITEM_NUM).collect(Collectors.toList());
//        List<List<Integer>> item_inputs=new ArrayList<>(new ArrayList<>());
//        for(Integer l:list){
//            item_inputs.add(Arrays.asList(l));
//        }
//
//        MkganRequest mkganRequest = new MkganRequest();
//        mkganRequest.setItem_inputs(item_inputs);
//        mkganRequest.setUser_inputs(user_inputs);
//        requestBody.put("inputs", mkganRequest);

//        Map<String,Integer> requestBody=new HashMap<>();
//        requestBody.put("user_id",1);

        String requestBody="user_id=1";

//        System.out.println(requestBody);
        // 发请求
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(requestUrl, request, String.class);


        List<BigDecimal> scoredList=new ArrayList<>();
        String results=response.getBody();
        String[] resultsArray=results.substring(1,results.length()-2).split(",");
//        JSONObject.toJavaObject(JSONObject.parseObject(response.getBody()), MkganResults.class)
//                .getOutputs()
//                .forEach(lb->scoredList.add(lb.get(0)));
        for(int i=0;i<resultsArray.length;i++){
            System.out.println(resultsArray[i]);
            scoredList.add(BigDecimal.valueOf(Double.parseDouble(resultsArray[i])));
        }
//        System.out.println(resultsArray.length);
//        System.out.println(scoredList);


        // 推荐列表
        System.out.println(topN(scoredList, 10));

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
//        System.out.println("\t recommendList="+recommendList);

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
