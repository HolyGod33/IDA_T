package com.zjut.ida.recommend.tutor.dao;

import com.zjut.ida.recommend.tutor.core.neo4j.Neo4jArticle;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author wly
 * @date 2021/4/29 14:44
 */
public interface Neo4jArticleRepository extends Repository<Neo4jArticle, Long> {
    @Query("match (n:Article)-[*1]-(m) where id(m)=$tutorId return n order by n.citeCount desc limit 5")
    List<Neo4jArticle> findTop5ByTutorId(@Param("tutorId") Long tutorId);
}
