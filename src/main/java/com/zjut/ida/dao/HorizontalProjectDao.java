package com.zjut.ida.dao;

import com.zjut.ida.entity.Article;
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
 * @author Casterx on 2019/10/27.
 */
@Repository
public interface HorizontalProjectDao extends Neo4jRepository<HorizontalProject,Long> {


//    @Query(value = "match (s:Scholar{name:{0}})-[]-(a:HorizontalProject) return a",
//            countQuery= "match (s:Scholar{name:{0}})-[]-(a:HorizontalProject) return count(a)")
//    Page<HorizontalProject> findHorizontalProjectsByScholarName(String scholarName, Pageable pageable);

    @Query(value = "match (s:Scholar{name:{0}})-[]-(a:HorizontalProject) return a order by a.startDate desc")
    List<HorizontalProject> findHorizontalProjectsByScholarName(String scholarName);

    List<HorizontalProject> findHorizontalProjectsByNameContains(String words);

    List<HorizontalProject> findHorizontalProjectsByCooperatorsContains(String words);


    @Query("match(n:HorizontalProject) where id(n)={0} return n")
    HorizontalProject findHorizontalProjectsById(@Param("horizontalProjectId")Integer horizontalProjectId);

    @Query("match(n:SysStudent)-[r:History]-(m:Scholar)\n" +
            "optional match(m)-[r1]-(m1:HorizontalProject)\n" +
            "with m,count(m) as history,m1\n" +
            "order by history desc\n" +
            "return m1 limit {0}")
    List<HorizontalProject> findColdStartByHistoryCount(int count);

}
