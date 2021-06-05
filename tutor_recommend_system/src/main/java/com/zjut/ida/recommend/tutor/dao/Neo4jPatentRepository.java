package com.zjut.ida.recommend.tutor.dao;

import com.zjut.ida.recommend.tutor.core.neo4j.Neo4jPatent;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author wly
 * @date 2021/4/29 15:00
 */
public interface Neo4jPatentRepository extends Repository<Neo4jPatent, Long> {
    @Query("match (n:Patent)-[*1]-(m) where id(m)=$tutorId return n order by n.firstInventor=m.name desc limit 5")
    List<Neo4jPatent> findTop5ByTutorId(@Param("tutorId") Long tutorId);
}
