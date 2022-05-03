package com.zjut.ida.recommend.tutor.m2ndao;

import com.zjut.ida.recommend.tutor.core.entity.SysDictClass;
import com.zjut.ida.recommend.tutor.core.m2nentity.NSysClass;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

/**
 * @author Casterx on 2022/5/2.
 */
public interface SysClassDao extends Neo4jRepository<NSysClass,Long> {

    @Query("match(n:SysClass) return distinct n.admissionYear order by n.admissionYear desc")
    List<Integer> findUniqueAdmissionYearList();

    List<NSysClass> findNSysClassByAdmissionYearAndCollegeNameOrderByClassName(Integer admissionYear, String collegeName);

}
