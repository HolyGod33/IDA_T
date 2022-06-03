package com.zjut.ida.academic_profile_system.service.impl;

import com.zjut.ida.academic_profile_system.dao.ScholarDao;
import com.zjut.ida.academic_profile_system.entity.Scholar;
import com.zjut.ida.academic_profile_system.service.ScholarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author kokoryh on 2022/5/29
 */
@Service
public class ScholarServiceImpl implements ScholarService {

    @Autowired
    private ScholarDao scholarDao;

    @Override
    @Transactional(readOnly = true)
    public Scholar findScholarByScholarId(Integer scholarId) {
        return scholarDao.findScholarByScholarId(scholarId);
    }

}
