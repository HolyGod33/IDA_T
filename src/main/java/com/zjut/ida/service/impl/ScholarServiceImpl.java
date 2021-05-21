package com.zjut.ida.service.impl;

import com.zjut.ida.dao.ScholarDao;
import com.zjut.ida.entity.*;
import com.zjut.ida.service.ScholarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author Casterx on 2019/10/10.
 */
@Service
public class ScholarServiceImpl implements ScholarService {

    @Autowired
    private ScholarDao scholarDao;





    @Override
    @Transactional(readOnly = true)
    public List<Scholar> findScholarsByNameContains(String scholarName) {
        return scholarDao.findScholarsByNameContains(scholarName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Partner> findPartnersByScholarName(String scholarName) {
        return scholarDao.findPartnersByScholarName(scholarName);
    }

    @Override
    public List<Partner> findPartnersByScholarNameForCount(String scholarName, Integer count) {
        return scholarDao.findPartnersByScholarNameForCount(scholarName, count);
    }

    @Override
    public Object findPartnersByScholarNameForCount1(String scholarName, Integer count) {
//        List<Map<String,Object>> partnerList=new ArrayList<>();
        List<Map<String, Object>> data = new ArrayList<>();
        List<Map<String, Object>> links = new ArrayList<>();
        Set<String> repeatScholarNameSet=new HashSet<>();

        List<Scholar> tempScholarList=scholarDao.findScholarsByNameContains(scholarName);
        if(tempScholarList!=null&&tempScholarList.size()!=0){
            Scholar origScholar=tempScholarList.get(0);

            Map<String,Object> origMap=nodesMap(origScholar,"orig");
            origMap.put("category",scholarName);
            data.add(origMap);
            repeatScholarNameSet.add(scholarName);
            System.out.println("origName="+scholarName);


            List<Partner> origPartnerList= scholarDao.findPartnersByScholarNameForCount(scholarName, count);
            for(int i=0;i<origPartnerList.size();i++){
                Partner tempPartner=origPartnerList.get(i);
                if(!repeatScholarNameSet.contains(tempPartner.getScholar().getName())){
                    Map<String,Object> childMap=nodesMap(tempPartner.getScholar(),"child");
                    if(tempPartner.getCount()>20){
                        childMap.put("category",scholarName);
                    }
                    data.add(childMap);
                    repeatScholarNameSet.add(tempPartner.getScholar().getName());
                    System.out.println("childName="+tempPartner.getScholar().getName());
                }
            }

//            Map<String,Object> origPartnerMap=new HashMap<>();
//            origPartnerMap.put("master",origScholar);
//            origPartnerMap.put("partner",origPartnerList);
//            partnerList.add(origPartnerMap);
            for(int i=0;i<origPartnerList.size();i++){
                Partner tempPartner=origPartnerList.get(i);


                links.add(linksMap(origScholar,tempPartner));


                String tempScholarName=tempPartner.getScholar().getName();
                List<Partner> tempPartnerList= scholarDao.findPartnersByScholarNameForCount(tempScholarName, count);
                for(int j=0;j<tempPartnerList.size();j++){
                    Partner tempPartnerJ=tempPartnerList.get(j);

                    if(!repeatScholarNameSet.contains(tempPartnerJ.getScholar().getName())){
                        Map<String,Object> childChildMap=nodesMap(tempPartnerJ.getScholar(),"child");
//                        if(tempPartner.getCount()>20){
//                            childChildMap.put("category",tempScholarName);
//                        }
                        data.add(childChildMap);
                        repeatScholarNameSet.add(tempPartnerJ.getScholar().getName());
                        System.out.println("childChildName="+tempPartnerJ.getScholar().getName());
                    }

                    links.add(linksMap(tempPartner.getScholar(),tempPartnerJ));

                }
            }
        }
        Map<String, Object> results = new HashMap<>();

        results.put("links", links);
        data= filterListById(data,"data");
        results.put("nodes",data);
        Set<String> categorySet=new HashSet<>();
        for(int i=0; i<data.size(); i++){
            Map<String, Object> map1 = data.get(i);
            if(map1.get("category")!=null){
                categorySet.add(map1.get("category").toString());
            }
        }
        results.put("category", categorySet);
        return results;
    }

    private Map<String, Object> nodesMap(Scholar scholar,String flag){
        Map<String, Object> map = new HashMap<>();
        map.put("name", scholar.getName());
        map.put("id", scholar.getId().toString());
//        map.put("category",scholar.getSubOrganization()==null?"null":scholar.getSubOrganization());
//        Map<String,Boolean> showMap=new HashMap<>();
//        showMap.put("show",true);
//        map.put("label",showMap);
        if("orig".equals(flag)){
            map.put("symbolSize",50);
        }
        return map;
    }

    private Map<String, Object> linksMap(Scholar scholar,Partner partner){
        Map<String, Object> map = new HashMap<>();
        map.put("source", scholar.getId().toString());
        map.put("target", partner.getScholar().getId().toString());
//        map.put("value",partner.getCount().toString());
        map.put("value",partner.getCount().toString());
        return map;
    }

    private  List<Map<String, Object>> filterListById(List<Map<String, Object>> list,String flag) {
        if(null==list || list.size()<=0){
            return list;
        }else{
            for(int i=0; i<list.size(); i++){
                Map<String, Object> map1 = list.get(i);
                for(int j=i+1; j<list.size(); j++){
                    if("data".equals(flag)){
                        if(map1.get("id")!=null&&list.get(j).get("id")!=null){
                            if(map1.get("id").equals(list.get(j).get("id"))){
                                list.remove(j);
                                j--;
                            }
                        }
                    }

                }
            }

            return list;
        }

    }

    @Override
    public List<String> findKeyWordsByScholarName(String scholarName) {
        List<String> keyWordsList = scholarDao.findKeyWordsByScholarName(scholarName);
        List<String> keyWordList = new LinkedList<>();
        if(keyWordList==null||keyWordList.size()==0){
            keyWordList.add("test11111");
            return keyWordList;
        }
        for (int i = 0; i < keyWordsList.size(); i++) {
            String keyWords = keyWordsList.get(i);
            String[] keyword = keyWords.split(",|；");
            if(keyword.length!=0||keyword!=null){
                keyWordList.add(keyword[0]);
            }
        }
        return keyWordList;
    }

    @Override
    public Object findKeyWordsByScholarName1(String scholarName) {
        List<String> keyWordsList = scholarDao.findKeyWordsByScholarName(scholarName);
        Hashtable hashtable=new Hashtable(keyWordsList.size()*5);
        for (int i = 0; i < keyWordsList.size(); i++) {
            String keyWords = keyWordsList.get(i);
            if(keyWords!=null){
                String[] keyword = keyWords.split(",|；");
                for(int j=0;j<keyword.length;j++){
                    if(hashtable.containsKey(keyword[j])){
                        int count=Integer.parseInt(hashtable.get(keyword[j]).toString());
                        hashtable.put(keyword[j],count+1);
                    }else{
                        hashtable.put(keyword[j],1);
                    }
                }
            }
        }
        return hashtable;
    }


    @Override
    @Transactional(readOnly = true)
    public List<PublishArticleCount> findPublishArticleCountByScholarName(String scholarName) {
        return scholarDao.findPublishArticleCountByScholarName(scholarName);
    }

    @Override
    @Transactional(readOnly = true)
    public Object findPublishArticleCountByScholarName1(String scholarName) {
        List<String> legend = new ArrayList<>();
        String[] xAxis={"2015","2016","2017","2018","2019"};
        List<Map<String, Object>> data = new ArrayList<>();
        List<Map<String,Object>> countList= scholarDao.findPublishArticleCountByScholarName1(scholarName);
        Map<String,Object> infoMap=new HashMap<>();
        for(int i=0;i<countList.size();i++){
            Map<String,Object> countMap=countList.get(i);
            String catalogName=countMap.get("catalogName")==null?"NoKind":countMap.get("catalogName").toString();
            System.out.println(catalogName);
            int[] yearCountList;
            if(infoMap.containsKey(catalogName)){
                yearCountList=(int[])infoMap.get(catalogName);
            }else{
                yearCountList=new int[5];
            }
            yearCountList[Integer.parseInt(countMap.get("year").toString())-2015]=(Integer.parseInt(countMap.get("count").toString()));
            infoMap.put(catalogName,yearCountList);
        }
        for(String key:infoMap.keySet()){
            Map<String,Object> dataMap=new HashMap<>();
            dataMap.put("name",key);
            legend.add(key);
            dataMap.put("type","line");
            dataMap.put("data",(int[])infoMap.get(key));
            data.add(dataMap);
        }
        Map<String, Object> results = new HashMap<>();
        results.put("data",data);
        results.put("xAxis",xAxis);
        results.put("legend",legend);
//        System.out.println(infoMap.get("自然科学"));
//        return scholarDao.findPublishArticleCountByScholarName1(scholarName);
        return results;
    }
}
