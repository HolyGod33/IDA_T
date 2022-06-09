package com.zjut.ida.service.impl;

import com.zjut.ida.dao.VerticalProjectDao;
import com.zjut.ida.entity.Patent;
import com.zjut.ida.entity.VerticalProject;
import com.zjut.ida.service.VerticalProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Casterx on 2019/10/23.
 */
@Service
public class VerticalProjectServiceImpl implements VerticalProjectService {

    @Autowired
    private VerticalProjectDao verticalProjectDao;

    @Override
    public List<VerticalProject> findVerticalProjectByScholarName(String scholarName) {
        return verticalProjectDao.findVerticalProjectByScholarName(scholarName);
    }

    @Override
    public List<VerticalProject> findVerticalProjectsById(List<Long> verticalProjectIdList) {
        List<VerticalProject> verticalProjectList=new ArrayList<>();
        for(Long id:verticalProjectIdList){
            VerticalProject temp=verticalProjectDao.findVerticalProjectsById(id.intValue());
            verticalProjectList.add(temp);
        }
        return verticalProjectList;
    }

    @Override
    public List<VerticalProject> findColdStartByHistoryCount(int topN) {
        return verticalProjectDao.findColdStartByHistoryCount(topN);
    }
}
