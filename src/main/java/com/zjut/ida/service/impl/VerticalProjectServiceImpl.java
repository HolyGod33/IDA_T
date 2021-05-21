package com.zjut.ida.service.impl;

import com.zjut.ida.dao.VerticalProjectDao;
import com.zjut.ida.entity.VerticalProject;
import com.zjut.ida.service.VerticalProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
