package com.zjut.ida.mkgan;

import com.alibaba.fastjson.JSONObject;
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

    private String MKAGN_BASE_URL ="http://10.12.45.49:8502/v1/models/mkgan";
    private String MKAGN_END_URL =":predict";
    
    private String ckanRequestUrl = "http://47.115.216.121:3000/predictions/CKAN";
    private String signatureName="serving_default";
    private Map<String,Integer> itemNumMap=new HashMap<String,Integer>(){
        {
            put(MkganConstant.SCHOLAR,167);
            put(MkganConstant.ARTICLE,2880);
            put(MkganConstant.PATENT,1839);
            put(MkganConstant.VP,926);
            put(MkganConstant.HP,780);
        }
    };



    @Autowired
    private RedisTemplate redisTemplate;

    private List<BigDecimal> MkagnGetScores(String type,String requestUrl, String studentId, int itemNum){
        RestTemplate restTemplate = new RestTemplate();
        List<BigDecimal> scoredList;
        // 请求头
        HttpHeaders headers = new HttpHeaders();

        if(type.equals(MkganConstant.SCHOLAR)){
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            String studentRemapId=getStudentRemapId(studentId);
            String requestBody="user_id="+studentRemapId;
            // 发请求
            HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(ckanRequestUrl, request, String.class);
            scoredList=new ArrayList<>();
            String results=response.getBody();
            String[] resultsArray=results.substring(1,results.length()-2).split(",");
            for(int i=0;i<resultsArray.length;i++){
//                System.out.println(resultsArray[i]);
                scoredList.add(BigDecimal.valueOf(Double.parseDouble(resultsArray[i])));
            }
        }else{
            headers.setContentType(MediaType.APPLICATION_JSON);
            // 请求体
            JSONObject requestBody = new JSONObject();
            requestBody.put("signature_name", signatureName);

            String studentRemapId=getStudentRemapId(studentId);
            List<Integer> user_inputs=Arrays.asList(Integer.parseInt(studentRemapId));
            List<Integer> list=Stream.iterate(0, item -> item + 1).limit(itemNum).collect(Collectors.toList());
            List<List<Integer>> item_inputs=new ArrayList<>(new ArrayList<>());
            for(Integer l:list){
                item_inputs.add(Arrays.asList(l));
            }

            MkganRequest mkganRequest = new MkganRequest();
            mkganRequest.setItem_inputs(item_inputs);
            mkganRequest.setUser_inputs(user_inputs);
            requestBody.put("inputs", mkganRequest);
            // 发请求
            HttpEntity<JSONObject> request = new HttpEntity<>(requestBody, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(requestUrl, request, String.class);
            scoredList=new ArrayList<>();
            JSONObject.toJavaObject(JSONObject.parseObject(response.getBody()), MkganResults.class)
                    .getOutputs()
                    .forEach(lb->scoredList.add(lb.get(0)));
        }
        return scoredList;
    }


    public List<Long> requestRecommendList(String type,Integer topN,String studentId){
        List<BigDecimal> scoredList= MkagnGetScores(type,MKAGN_BASE_URL+type+MKAGN_END_URL,studentId,itemNumMap.get(type));
        return topN(type,scoredList, topN);
    }

    //ckan的导师推荐
//    public List<Long> requestCKANRecommendList(String type,Integer topN,String studentId){
//        RestTemplate restTemplate = new RestTemplate();
//        // 请求头
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//        // 请求体
//        String studentRemapId=getStudentRemapId(studentId);
//        String requestBody="user_id="+studentRemapId;
//
//        // 发请求
//        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
//        ResponseEntity<String> response = restTemplate.postForEntity(ckanRequestUrl, request, String.class);
//
//
//        List<BigDecimal> scoredList=new ArrayList<>();
//        String results=response.getBody();
//        String[] resultsArray=results.substring(1,results.length()-2).split(",");
//        for(int i=0;i<resultsArray.length;i++){
//            System.out.println(resultsArray[i]);
//            scoredList.add(BigDecimal.valueOf(Double.parseDouble(resultsArray[i])));
//        }
//
//
//        // 推荐列表
//        return topN(type,scoredList, topN);
//
//
//    }





    private List<Long> topN(String type,List<BigDecimal> scoredList, int topN) {
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
            recommendList.add(getItemRemapId(type,itemIndex.toString()));
        }
//        System.out.println("\t recommendList="+recommendList);

        return recommendList;
    }



    /**
     * 获取学生重映射 id
     *
     * @return 学生重映射 id
     */
    private String getStudentRemapId(String studentId) {
        return String.valueOf(redisTemplate.opsForValue().get("user_"+studentId));
    }

    private Long getItemRemapId(String type,String tutorId) {
        return Long.parseLong(redisTemplate.opsForValue().get(type+"_"+tutorId).toString());
    }

}
