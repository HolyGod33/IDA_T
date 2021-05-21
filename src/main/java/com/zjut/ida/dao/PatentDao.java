package com.zjut.ida.dao;

import com.zjut.ida.entity.Patent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Casterx on 2019/10/27.
 */
@Repository
public interface PatentDao extends Neo4jRepository<Patent,Long> {

//    @Query(value = "match (s:Scholar{name:{0}})-[]-(a:Patent) return a",
//            countQuery= "match (s:Scholar{name:{0}})-[]-(a:Patent) return count(a)")
//    Page<Patent> findPatentByScholarName(String scholarName, Pageable pageable);

    @Query(value = "match (s:Scholar{name:{0}})-[]-(a:Patent) return a order by a.applyDate desc")
    List<Patent> findPatentByScholarName(String scholarName);

    List<Patent> findPatentsByNameContains(String words);

    List<Patent> findPatentsByInventorsContains(String words);

}