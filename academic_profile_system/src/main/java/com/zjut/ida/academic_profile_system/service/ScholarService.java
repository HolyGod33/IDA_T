package com.zjut.ida.academic_profile_system.service;

import com.zjut.ida.academic_profile_system.entity.Scholar;

/**
 * @author kokoryh on 2022/5/9
 */
public interface ScholarService {

    Scholar findScholarByScholarId(Integer scholarId);

}
