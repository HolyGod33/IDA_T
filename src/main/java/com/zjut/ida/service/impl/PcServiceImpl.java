package com.zjut.ida.service.impl;

import com.zjut.ida.dao.PcDao;
import com.zjut.ida.entity.Community;
import com.zjut.ida.entity.IdaCommunity;
import com.zjut.ida.service.PcService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.beanutils.BeanMap;

import java.lang.reflect.Field;
import java.util.*;

import static com.zjut.ida.tool.ObjectToMap.objectToMap;

/**
 * @author Casterx on 2020/7/17.
 */
@Service
public class PcServiceImpl implements PcService {

    @Autowired
    private PcDao pcDao;

    @Override
    public Object findCommunity(String scholarName) {
        String communityName = pcDao.findCommunityName(scholarName);
        List<Map<String, Object>> pcList=pcDao.findCommunity(communityName);
//        Set<String> repeatScholarNameSet=new HashSet<>();
        List<Map<String, Object>> data = new ArrayList<>();
        List<Map<String, Object>> links = new ArrayList<>();
        for(int i=0;i<pcList.size();i++){
            Map<String, Object> pc=pcList.get(i);
            Map<String, Object> source=objectToMap(pc.get("source"));
            Map<String, Object> target=objectToMap(pc.get("target"));
            Map<String, Object> link=objectToMap(pc.get("link"));
            data.add(source);
            data.add(target);
            links.add(link);
        }
        Map<String, Object> results = new HashMap<>();

        Set<String> categorySet=new HashSet<>();
        for(int i=0; i<data.size(); i++){
            Map<String, Object> map1 = data.get(i);
            if(map1.get("community")!=null){
                categorySet.add(map1.get("community").toString());
            }
        }
        results.put("community", categorySet);

        results.put("links", links);
        data= filterListById(data,"data");
        results.put("nodes",data);
//        return pcDao.findCommunity(organization,affiliate);
        return results;
    }

    @Override
    public Object findAllCommunity() {
        List<String> communityName = pcDao.findAllCommunity();
//        System.out.println(communityName.size());
        List<Map<String, Object>> data = new ArrayList<>();
        List<Map<String, Object>> links = new ArrayList<>();
        Map<String, Object> results = new HashMap<>();
        for(String c:communityName){
            List<Map<String, Object>> pcList=pcDao.findCommunity(c);
            for(int i=0;i<pcList.size();i++){
                Map<String, Object> pc=pcList.get(i);
                Map<String, Object> source=objectToMap(pc.get("source"));
                Map<String, Object> target=objectToMap(pc.get("target"));
                Map<String, Object> link=objectToMap(pc.get("link"));
                data.add(source);
                data.add(target);
                links.add(link);
            }
            Set<String> categorySet=new HashSet<>();
            for(int i=0; i<data.size(); i++){
                Map<String, Object> map1 = data.get(i);
                if(map1.get("community")!=null){
                    categorySet.add(map1.get("community").toString());
                }
            }
            results.put("community", categorySet);
            results.put("links", links);
            data= filterListById(data,"data");
            results.put("nodes",data);
        }
        return results;
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





}
