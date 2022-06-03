package com.zjut.ida.academic_profile_system.service.impl;

import com.zjut.ida.academic_profile_system.dao.ApsScholarDao;
import com.zjut.ida.academic_profile_system.service.ApsScholarService;
import com.zjut.ida.entity.Scholar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author kokoryh on 2022/5/29
 */
@Service
public class ApsScholarServiceImpl implements ApsScholarService {

    @Autowired
    private ApsScholarDao scholarDao;

    @Override
    @Transactional(readOnly = true)
    public Scholar findScholarByScholarId(Integer scholarId) {
        return scholarDao.findScholarByScholarId(scholarId);
    }

}
