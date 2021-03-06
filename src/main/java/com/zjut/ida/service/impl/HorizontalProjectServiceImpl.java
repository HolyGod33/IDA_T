package com.zjut.ida.service.impl;

import com.zjut.ida.dao.HorizontalProjectDao;
import com.zjut.ida.entity.HorizontalProject;
import com.zjut.ida.entity.Patent;
import com.zjut.ida.service.HorizontalProjectService;
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
public class HorizontalProjectServiceImpl implements HorizontalProjectService {

    @Autowired
    private HorizontalProjectDao horizontalProjectDao;

    @Override
    public List<HorizontalProject> findHorizontalProjectsByScholarName(String scholarName) {
        return horizontalProjectDao.findHorizontalProjectsByScholarName(scholarName);
    }

    @Override
    public List<HorizontalProject> findHorizontalProjectsById(List<Long> horizontalProjectIdList) {
        List<HorizontalProject> horizontalProjectList=new ArrayList<>();
        for(Long id:horizontalProjectIdList){
            HorizontalProject temp=horizontalProjectDao.findHorizontalProjectsById(id.intValue());
            horizontalProjectList.add(temp);
        }
        return horizontalProjectList;
    }

    @Override
    public List<HorizontalProject> findColdStartByHistoryCount(int topN) {
        return horizontalProjectDao.findColdStartByHistoryCount(topN);
    }
}
