package com.zjut.ida.academic_profile_system.dao;

import com.zjut.ida.entity.Scholar;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kokoryh on 2022/5/29
 */
@Repository
public interface ApsScholarDao extends Neo4jRepository<Scholar, Long> {

    // 根据scholar的id查询其信息
    @Query("match(n:Scholar) where id(n)={0} return n")
    Scholar findScholarByScholarId(@Param("scholarId") Integer scholarId);

    // 根据scholar的id查询其关联的作者
    @Query("match (s1:Scholar)-[]-(a1:Article) where id(s1)={0} with a1 match p=(a1)-[]-(s2:Scholar) return distinct(s2.name)")
    List<String> findRelationByScholarId(@Param("scholarId") Integer scholarId);

}
