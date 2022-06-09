package com.zjut.ida.service.impl;

import com.zjut.ida.dao.PatentDao;
import com.zjut.ida.entity.Article;
import com.zjut.ida.entity.Patent;
import com.zjut.ida.service.PatentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Casterx on 2019/10/27.
 */
@Service
public class PatentServiceImpl implements PatentService {

    @Autowired
    private PatentDao patentDao;

    @Override
    public List<Patent> findPatentByScholarName(String scholarName) {
        return patentDao.findPatentByScholarName(scholarName);
    }

    @Override
    public List<Patent> findPatentsById(List<Long> patentIdList) {
        List<Patent> patentList=new ArrayList<>();
        for(Long id:patentIdList){
            Patent temp=patentDao.findPatentsById(id.intValue());
            patentList.add(temp);
        }
        return patentList;
    }

    @Override
    public List<Patent> findColdStartByHistoryCount(int topN) {
        return patentDao.findColdStartByHistoryCount(topN);
    }
}
