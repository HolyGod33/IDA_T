package com.zjut.ida.dao;

import com.zjut.ida.entity.Patent;
import com.zjut.ida.entity.Patent;
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
public interface PatentDao extends Neo4jRepository<Patent,Long> {

//    @Query(value = "match (s:Scholar{name:{0}})-[]-(a:Patent) return a",
//            countQuery= "match (s:Scholar{name:{0}})-[]-(a:Patent) return count(a)")
//    Page<Patent> findPatentByScholarName(String scholarName, Pageable pageable);

    @Query(value = "match (s:Scholar{name:{0}})-[]-(a:Patent) return a order by a.applyDate desc")
    List<Patent> findPatentByScholarName(String scholarName);

    List<Patent> findPatentsByNameContains(String words);

    List<Patent> findPatentsByInventorsContains(String words);

    @Query("match(n:Patent) where id(n)={0} return n")
    Patent findPatentsById(@Param("patentId")Integer patentId);

    @Query("match(n:SysStudent)-[r:History]-(m:Scholar)\n" +
            "optional match(m)-[r1]-(m1:Patent)\n" +
            "with m,count(m) as history,m1\n" +
            "order by history desc\n" +
            "return m1 limit {0}")
    List<Patent> findColdStartByHistoryCount(int count);

}