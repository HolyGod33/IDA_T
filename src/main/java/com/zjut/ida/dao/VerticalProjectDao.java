package com.zjut.ida.dao;

import com.zjut.ida.entity.VerticalProject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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

}
