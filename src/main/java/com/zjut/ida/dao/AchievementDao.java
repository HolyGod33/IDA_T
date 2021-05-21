package com.zjut.ida.dao;

import com.zjut.ida.entity.*;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Veloma on 2020/10/21.
 */
@Repository
public interface AchievementDao extends Neo4jRepository<Achievement,Long> {


    @Query("MATCH(a:HorizontalProject) WHERE (a.cooperators Contains {0} OR a.leader Contains {0}) AND (a.cooperators Contains {1} OR a.leader Contains {1}) RETURN a As horizontalProject")
    List<HorizontalProject> findHorizontalProjectsByScholarName(String name1, String name2);


    @Query("MATCH(a:VerticalProject) WHERE (a.cooperators Contains {0} OR a.leader Contains {0} ) AND (a.cooperators Contains {1} OR a.leader Contains {1} ) RETURN a As verticalProject")
    List<VerticalProject> findVerticalProjectsByScholarName(String name1, String name2);


    @Query("MATCH(s1:Scholar)-[r1]-(a:Article)-[r2]-(s2:Teacher) WHERE s1.name={0} AND s2.name<>{0} AND s2.name={1} RETURN a As article")
    List<Article> findArticlesByScholarName(String name1, String name2);


    @Query("MATCH(a:Patent) WHERE (a.inventors Contains {0} OR a.firstInventor Contains {0} ) AND (a.inventors Contains {1} OR a.firstInventor Contains {1} ) RETURN a As patent")
    List<Patent> findPatentsByScholarName(String name1, String name2);


    @Query("MATCH (n:Scholar) where id(n)={0} RETURN n As scholar")
    Scholar findScholarByScholarId(Long id);

}
