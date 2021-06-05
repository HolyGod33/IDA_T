package com.zjut.ida.recommend.tutor.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjut.ida.recommend.tutor.core.entity.SysStudentFavorite;
import org.apache.ibatis.annotations.Param;

/**
 * @author wly
 * @date 2021/5/4 12:26
 */
public interface SysStudentFavoriteMapper extends BaseMapper<SysStudentFavorite> {

    /**
     * 判断该导师是否被收藏
     *
     * @param studentId    学生学号
     * @param tutorNeo4jId 导师neo4j id
     * @return 布尔
     */
    Boolean exist(@Param("studentId") String studentId, @Param("tutorNeo4jId") Long tutorNeo4jId);
}
