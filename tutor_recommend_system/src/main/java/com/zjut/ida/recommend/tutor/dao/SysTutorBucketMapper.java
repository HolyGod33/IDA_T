package com.zjut.ida.recommend.tutor.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjut.ida.recommend.tutor.core.entity.SysTutorBucket;

/**
 * LSH分桶 Mapper 接口
 *
 * @author wly
 * @date 2021/5/16 13:43
 */
@Deprecated
public interface SysTutorBucketMapper extends BaseMapper<SysTutorBucket> {

    /**
     * 找到导师对应的桶
     *
     * @param tutorNeo4jId 导师在 neo4j 中的id
     * @return 桶 id
     */
    Integer findBucketByNeo4jId(Long tutorNeo4jId);
}
