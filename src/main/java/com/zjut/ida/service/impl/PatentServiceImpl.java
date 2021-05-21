package com.zjut.ida.service.impl;

import com.zjut.ida.dao.PatentDao;
import com.zjut.ida.entity.Patent;
import com.zjut.ida.service.PatentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
