package com.zjut.ida.recommend.tutor.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjut.ida.recommend.tutor.core.entity.SysStudent;
import org.apache.ibatis.annotations.Param;

/**
 * @author wly
 * @date 2021/4/22 10:42
 */
public interface SysStudentMapper extends BaseMapper<SysStudent> {

    /**
     * 按学生学号找到学生的重映射id
     * @param studentId 学生学号
     * @return 学生重映射id
     */
    Long findStudentRemapIdByStudentId(@Param("studentId") String studentId);
}
