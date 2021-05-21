package com.zjut.ida.service.impl;

import com.zjut.ida.dao.HorizontalProjectDao;
import com.zjut.ida.entity.HorizontalProject;
import com.zjut.ida.service.HorizontalProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
