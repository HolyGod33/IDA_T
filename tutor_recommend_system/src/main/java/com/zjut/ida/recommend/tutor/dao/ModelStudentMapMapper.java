package com.zjut.ida.recommend.tutor.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjut.ida.recommend.tutor.core.entity.ModelStudentMap;
import org.apache.ibatis.annotations.Param;

/**
 * @author wly
 * @date 2021/4/26 14:05
 */
public interface ModelStudentMapMapper extends BaseMapper<ModelStudentMap> {
    /**
     * 按重映射id找到学生学号
     * @param remapId 重映射id
     * @return 学生学号
     */
    String findStudentIdByRemapId(@Param("remapId") Long remapId);

    /**
     * 按学生学号找到重映射id
     * @param studentId 学生学号
     * @return 重映射id
     */
    Long findRemapIdByStudentId(String studentId);
}
