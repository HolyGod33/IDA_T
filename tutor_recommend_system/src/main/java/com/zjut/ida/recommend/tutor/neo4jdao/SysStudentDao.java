package com.zjut.ida.recommend.tutor.neo4jdao;

import com.zjut.ida.recommend.tutor.core.entity.SysStudent;
import com.zjut.ida.recommend.tutor.core.neo4jentity.NSysStudent;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Casterx on 2022/5/1.
 */
@Repository
public interface SysStudentDao extends Neo4jRepository<NSysStudent,Long> {


//    List<NSysStudent> findScholarsByNameContains(@Param("scholarName") String scholarName);

    NSysStudent findNSysStudentByStudentId(@Param("scholarID")String studentId);

    NSysStudent findNSysStudentById(Long id);



}
