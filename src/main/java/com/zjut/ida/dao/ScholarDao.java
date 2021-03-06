package com.zjut.ida.dao;


import com.zjut.ida.entity.HorizontalProject;
import com.zjut.ida.entity.Partner;
import com.zjut.ida.entity.PublishArticleCount;
import com.zjut.ida.entity.Scholar;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Casterx on 2019/10/10.
 */
@Repository
public interface ScholarDao extends Neo4jRepository<Scholar,Long> {

    //Scholar页面的基本信息
    List<Scholar> findScholarsByNameContains(@Param("scholarName") String scholarName);

    //All页面展示
    List<Scholar> findScholarsByDoMainContains(@Param("words")String words);

    //Scholar页面的合作关系
    @Query("match(s1:Scholar)-[r1]-(a)-[r2]-(s2:Scholar) where s1.name={0} and s2.name<>{0} return s2 as scholar,count(s2) as count order by count desc limit 4")
    List<Partner> findPartnersByScholarName(@Param("scholarName")String scholarName);

    //Scholar页面的发表年份
    @Query("match(n:Scholar)-[]-(a:Article) where n.name={0} and a.year is not null return a.year as year,count(a.year) as count order by year desc limit 4")
    List<PublishArticleCount> findPublishArticleCountByScholarName(@Param("scholarName")String scholarName);


    //Scholar页面的发表年份
    @Query("match (s:Scholar{name:{0}})-[]-(m:Article) optional match(m)-[]-(j:Journal)-[]-(c:Catalog)-[]-(c1:Catalog)  with c1.name as catalogName,m.year as year,count(*) as count\n" +
            "where year in ['2015','2016','2017','2018','2019']  return catalogName,year,count order by year desc")
    List<Map<String,Object>> findPublishArticleCountByScholarName1(@Param("scholarName")String scholarName);


    @Query("match(n:Scholar{name:{0}})-[]-(m:Article) return m.keyWord order by m.citeCount desc")
    List<String> findKeyWordsByScholarName(@Param("scholarName")String scholarName);

    @Query("MATCH(s1:Scholar)-[r1]-(a)-[r2]-(s2:Teacher) WHERE s1.name={0} AND s2.name<>{0} AND NOT (a:Organization) RETURN s2 AS scholar,count(a) AS count ORDER BY count DESC LIMIT {1}")
    List<Partner> findPartnersByScholarNameForCount(String scholarName, int count);

    @Query("match(n:Scholar) where id(n)={0} return n")
    Scholar findScholarsById(@Param("scholarId")Integer scholarId);

    @Query("match(n:SysStudent)-[r:History]-(m:Scholar) \n" +
            "with m,count(m) as historyCount\n" +
            "order by historyCount desc\n" +
            "return m limit {0}")
    List<Scholar> findScholarsByHistoryCount(int count);


    @Query("match(n:Scholar) where id(n)={0} with n\n" +
            "optional match (m)-[r:History]-(n:Scholar) \n" +
            "return n as scholar,count(r) as history")
    List<Map<String,Object>> findHistoryCountByScholarId(@Param("scholarId")Integer scholarId);


    @Query("match(n:Scholar) where id(n) in {0}\n" +
            "with n\n" +
            "optional match (m)-[r:History]-(n:Scholar)\n" +
            "return n as scholar,count(r) as history limit 10")
    List<Map<String,Object>> findHistoryCountByScholarIdList(List<Long> scholarIdList);

//    @Query("match(n)-[r:History]-(m) where id(m)={0} return count(r) as count")
//    List<Integer> findHistoryCount(Long scholarId);

    @Query("match(n:SysStudent)-[r:History]-(m:Scholar) with m,count(m) as history order by history desc return m as scholar, history limit 10")
    List<Map<String,Object>> findColdStartByHistoryCount(int topN);

    @Query("match(n:SysStudent)-[r:Choose]-(m:Scholar) where id(m)={0} return n.name as studentName,n.className as studentClassName")
    List<Map<String,Object>> findStudentByScholarId(Long scholarId);

}
