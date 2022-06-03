package com.zjut.ida.academic_profile_system.service;

import java.util.List;
import java.util.Map;

/**
 * @author kokoryh on 2022/5/8
 */
public interface DataService {

    List<List<Object>> findInterestByScholarId(Integer scholarId);

    List<Object> findRelationByScholarId(Integer scholarId);

    List<Object> findCoAuthorByScholarId(Integer scholarId);

    Map<String, Object> findStatisticsByScholarId(Integer scholarId);

    List<Object> findSimilarScholarByScholarId(Integer scholarId);

}
