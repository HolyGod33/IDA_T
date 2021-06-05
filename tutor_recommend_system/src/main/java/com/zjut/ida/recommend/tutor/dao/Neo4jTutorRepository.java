package com.zjut.ida.recommend.tutor.dao;

import com.zjut.ida.recommend.tutor.core.neo4j.Neo4jTutor;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

/**
 * @author wly
 * @date 2021/4/28 20:00
 */
public interface Neo4jTutorRepository extends Repository<Neo4jTutor, Long> {
    /**
     * 按 neo4j id查找导师信息
     *
     * @param neo4jId neo4j自动生成的id
     * @return 导师实体列表
     */
    @Query("match (n:Teacher) where id(n)=$neo4jId return n")
    Neo4jTutor findOneByNeo4jId(@Param("neo4jId") Long neo4jId);
}
