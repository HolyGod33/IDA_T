package com.zjut.ida.recommend.tutor.m2ndao;

import com.zjut.ida.recommend.tutor.core.m2nentity.NSysClass;
import com.zjut.ida.recommend.tutor.core.m2nentity.NSysCollege;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

/**
 * @author Casterx on 2022/5/2.
 */
public interface SysCollegeDao extends Neo4jRepository<NSysCollege,Long> {


    @Query("match(n:SysCollege) return n.collegeName")
    List<String> findCollegeNameList();

}
