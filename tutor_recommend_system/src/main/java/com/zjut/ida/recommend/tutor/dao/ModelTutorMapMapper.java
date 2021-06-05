package com.zjut.ida.recommend.tutor.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjut.ida.recommend.tutor.core.entity.ModelTutorMap;

import java.util.List;

/**
 * @author wly
 * @date 2021/4/26 14:05
 */
public interface ModelTutorMapMapper extends BaseMapper<ModelTutorMap> {

    /**
     * 按重映射id查询neo4j id
     *
     * @param remapIdList 教师重映射id
     * @return 教师neo4j id
     */
    @Deprecated
    List<Long> findNeo4jIdsByRemapIds(List<Integer> remapIdList);

    /**
     * 按重映射id查询neo4j id
     *
     * @param remapId 教师重映射id
     * @return 教师neo4j id
     */
    Long findNeo4jIdByRemapId(Long remapId);

    /**
     * 按 neo4j id 找到重映射 id
     *
     * @param tutorNeo4jId 教师neo4j id
     * @return 教师重映射id
     */
    Long findRemapIdByNeo4jId(Long tutorNeo4jId);
}
