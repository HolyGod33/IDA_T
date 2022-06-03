package com.zjut.ida.academic_profile_system.dao;

import com.zjut.ida.entity.Article;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author kokoryh on 2022/5/8
 */
@Repository
public interface ApsArticleDao extends Neo4jRepository<Article, Long> {

    // 根据article的id查询其信息及作者信息
    @Query("match p=(a:Article)-[]-(s:Scholar) where id(a)={0} return p")
    Article findArticleByArticleId(@Param("articleId") Integer articleId);

    // 根据scholar的id查询其发表的论文及论文的所有作者信息
    @Query("call apoc.cypher.run({cql}, null) yield value return value.p")
    List<Article> findArticlesByScholarId(@Param("cql") String cql);

    // 根据scholar的id查询其发表论文的年份和年内的数量
    @Query("match (s1:Scholar)-[]-(a1:Article) where id(s1)={0} and a1.year is not null return a1.year as year,count(*) as count order by year desc")
    List<Map<String, Object>> findPublishArticleCountByScholarId(@Param("scholarId") Integer scholarId);

}
