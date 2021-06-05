package com.zjut.ida.recommend.tutor.dao;

import com.zjut.ida.recommend.tutor.core.neo4j.Neo4jHorizontalProject;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author wly
 * @date 2021/4/29 15:44
 */
public interface Neo4jHorizontalProjectRepository extends Repository<Neo4jHorizontalProject, String> {
    @Query("match (n:HorizontalProject)-[*1]-(m) where id(m)=$tutorId return n order by n.leader=m.name desc limit 5")
    List<Neo4jHorizontalProject> findTop5ByTutorId(@Param("tutorId") Long tutorId);
}
