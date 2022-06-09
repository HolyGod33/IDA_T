package com.zjut.ida.dao;

import com.zjut.ida.entity.Article;
import com.zjut.ida.entity.PublishArticleCount;
import com.zjut.ida.entity.Scholar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Casterx on 2019/10/17.
 */
@Repository
public interface ArticleDao extends Neo4jRepository<Article,Long> {


//    @Query(value = "match (s:Scholar{name:{0}})-[:Publish]-(a:Article) return a",
//            countQuery= "match (s:Scholar{name:{0}})-[:Publish]-(a:Article) return count(a)")
//    Page<Article> findArticlesByScholarName(String scholarName, Pageable pageable);



//    @Query(value = "match (n:Scholar{name:{0}})-[p:Publish]->(a:Article) optional match (a)-[ps:Published]->(j:Journal)<-[ct:Contains]-(c:Catalog) return n,p,a,ps,j,ct,c order by c,a.year desc,c.weight desc,j.weight desc",
//            countQuery = "match (n:Scholar{name:{0}})-[p:Publish]->(a:Article) optional match (a)-[ps:Published]->(j:Journal)<-[ct:Contains]-(c:Catalog) return count(a)")
//    Page<Article> findArticlesByScholarName(String scholarName, Pageable pageable);


    //Scholar页面——根据学者姓名查询论文，并按照论文所属期刊和目录的权重排列
    @Query(value = "match (n:Scholar{name:{0}})-[p:Publish]->(a:Article) optional match (a)-[ps:Published]->(j:Journal)<-[ct:Contains]-(c:Catalog) return n,p,a,ps,j,ct,c order by c",
            countQuery = "match (n:Scholar{name:{0}})-[p:Publish]->(a:Article) optional match (a)-[ps:Published]->(j:Journal)<-[ct:Contains]-(c:Catalog) return count(a)")
    List<Article> findArticlesByScholarName(String scholarName);


    //TODO 和ScholarDao中的发表年份查询结果不统一。
//    @Query(value = "match p=(s:Scholar)-[:Publish]-(a:Article)-[:Published]-(j:Journal) where s.name={scholarName} and a.year={year} return p",
//            countQuery= "match p=(s:Scholar)-[:Publish]-(a:Article)-[:Published]-(j:Journal) where s.name={scholarName} and a.year={year} return count(p)")
    @Query(value = "match (s:Scholar{name:{scholarName}})-[:Publish]-(a:Article{year:{year}}) optional match (a)-[ps:Published]->(j:Journal) return a,ps,j")
    List<Article> findArticlesByScholarNameAndYear(@Param("scholarName")String scholarName, @Param("year")String year);


    //All页面展示
    @Query("match p=(a:Article)-[]-(:Journal) where a.title contains {0} return p")
    List<Article> findArticlesByTitleContains(@Param("words")String words);

    //All页面展示
    @Query("match p=(a:Article)-[]-(:Journal) where a.keyWord contains {0} return p")
    List<Article> findArticlesByKeyWordContains(@Param("words")String words);

    //All页面展示
    @Query("match p=(s:Scholar)-[]-(a:Article)-[]-(:Journal) where s.name contains {0} return p")
    List<Article> findArticlesByScholarWords(@Param("words")String words);


    @Query("match(n:SysStudent)-[r:History]-(m:Scholar)\n" +
            "optional match(m)-[r1]-(m1:Article)\n" +
            "with m,count(m) as historyCount,m1\n" +
            "order by historyCount desc\n" +
            "return m1 limit {0}")
    List<Article> findArticlesByHistoryColdStart(int count);


    @Query("match(n:SysStudent)-[r:History]-(m:Scholar)\n" +
            "optional match(m)-[r1]-(m1:Article)\n" +
            "with m,count(m) as history,m1\n" +
            "order by history desc\n" +
            "return m1 limit {0}")
    List<Article> findColdStartByHistoryCount(int count);

    @Query("match(n:Article) where id(n)={0} return n")
    Article findArticlesById(@Param("articleId")Integer articleId);

}
