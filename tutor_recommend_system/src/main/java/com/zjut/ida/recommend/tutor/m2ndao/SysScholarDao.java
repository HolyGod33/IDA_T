package com.zjut.ida.recommend.tutor.m2ndao;

import com.zjut.ida.recommend.tutor.core.m2nentity.NSysScholar;
import com.zjut.ida.recommend.tutor.core.m2nentity.NSysStudent;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Casterx on 2022/5/1.
 */
@Repository
public interface SysScholarDao extends Neo4jRepository<NSysScholar,Long> {


    @Query("match(n:Scholar) where n.tutorRemapId={0} return n.tutorRelativeIdList")
    String findTutorRelativeIdListByTutorRemapId(Long tutorRemapId);


    /**
     * 按重映射id查询neo4j id
     *
     * @param remapIdList 教师重映射id
     * @return 教师neo4j id
     */
    @Deprecated
    @Query("match(n:Scholar) where n.tutorRemapId in {0} return id(n)")
    List<Long> findNeo4jIdsByRemapIds(List<Integer> remapIdList);

    /**
     * 按重映射id查询neo4j id
     *
     * @param remapId 教师重映射id
     * @return 教师neo4j id
     */
    @Query("match(n:Scholar) where n.tutorRemapId = {0} return id(n)")
    Long findNeo4jIdByRemapId(Long remapId);

    /**
     * 按 neo4j id 找到重映射 id
     *
     * @param tutorNeo4jId 教师neo4j id
     * @return 教师重映射id
     */
    @Query("match(n:Scholar) where id(n)= {0} return n.tutorRemapId")
    Long findRemapIdByNeo4jId(Long tutorNeo4jId);


}
