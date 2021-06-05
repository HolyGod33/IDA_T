package com.zjut.ida.recommend.tutor.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjut.ida.recommend.tutor.core.entity.SysCF;

import java.util.List;

/**
 * 协同过滤数据 Mapper 接口
 *
 * @author wly
 * @date 2021/4/22 10:42
 */
public interface SysCFMapper extends BaseMapper<SysCF> {
    /**
     * 按学生学号查询30天内有过交互的导师重映射id
     * @param studentId 学生学号
     * @return 导师重映射id
     */
    List<Integer> findTutorRemapIdListByStudentId(String studentId);

    /**
     * 按学生学号查询30天内有过交互的导师neo4j id
     * @param studentId 学生学号
     * @return 导师neo4j id
     */
    List<Long> findTutorNeo4jIdListByStudentId(String studentId);

    /**
     * 是否有足够的的协同过滤数据
     *
     * @return 布尔
     */
    boolean hasEnoughHistory(String studentId);
}
