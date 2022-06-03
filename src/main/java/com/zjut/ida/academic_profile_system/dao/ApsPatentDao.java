package com.zjut.ida.academic_profile_system.dao;

import com.zjut.ida.entity.Patent;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author kokoryh on 2022/5/30
 */
@Repository
public interface ApsPatentDao extends Neo4jRepository<Patent, Long> {

    // 根据scholar的id查询其专利及专利的所有作者信息
    @Query("call apoc.cypher.run({cql}, null) yield value return value.p")
    List<Patent> findPatentsByScholarId(@Param("cql") String cql);

    // 根据scholar的id查询其专利的年份和年内的数量
    @Query("match (s1:Scholar)-[]-(p1:Patent) where id(s1)={0} and p1.applyDate is not null return left(p1.applyDate,4) as year,count(*) as count order by year desc")
    List<Map<String, Object>> findPublishPatentCountByScholarId(@Param("scholarId") Integer scholarId);

}
