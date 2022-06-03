package com.zjut.ida.academic_profile_system.service;

import com.zjut.ida.academic_profile_system.entity.Patent;

import java.util.List;
import java.util.Map;

/**
 * @author kokoryh on 2022/5/30
 */
public interface PatentService {

    List<Patent> findPatentsByScholarId(Integer scholarId, Integer limit, String year);

    List<Map<String, Object>> findPublishPatentCountByScholarId(Integer scholarId);

}
