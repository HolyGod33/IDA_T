package com.zjut.ida.recommend.tutor.m2ndao;

import com.zjut.ida.recommend.tutor.core.m2nentity.NSysStudent;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Casterx on 2022/5/1.
 */
@Repository
public interface SysStudentDao extends Neo4jRepository<NSysStudent,Long> {


//    List<NSysStudent> findScholarsByNameContains(@Param("scholarName") String scholarName);

    NSysStudent findNSysStudentByStudentId(@Param("scholarID")String studentId);

    NSysStudent findNSysStudentById(Long id);



}
