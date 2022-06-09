package com.zjut.ida.dao;

import com.zjut.ida.entity.HorizontalProject;
import com.zjut.ida.entity.VerticalProject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Casterx on 2019/10/23.
 */
@Repository
public interface VerticalProjectDao extends Neo4jRepository<VerticalProject,Long> {

//    @Query(value = "match (s:Scholar{name:{0}})-[]-(a:VerticalProject) return a",
//            countQuery= "match (s:Scholar{name:{0}})-[]-(a:VerticalProject) return count(a)")
//    Page<VerticalProject> findVerticalProjectByScholarName(String scholarName, Pageable pageable);

    @Query(value = "match (s:Scholar{name:{0}})-[]-(a:VerticalProject) return a order by a.startDate desc")
    List<VerticalProject> findVerticalProjectByScholarName(String scholarName);

    List<VerticalProject> findVerticalProjectsByNameContains(String words);

    List<VerticalProject> findVerticalProjectsByCooperatorsContains(String words);

    @Query("match(n:VerticalProject) where id(n)={0} return n")
    VerticalProject findVerticalProjectsById(@Param("verticalProjectId")Integer verticalProjectId);


    @Query("match(n:SysStudent)-[r:History]-(m:Scholar)\n" +
            "optional match(m)-[r1]-(m1:VerticalProject)\n" +
            "with m,count(m) as history,m1\n" +
            "order by history desc\n" +
            "return m1 limit {0}")
    List<VerticalProject> findColdStartByHistoryCount(int count);
}
