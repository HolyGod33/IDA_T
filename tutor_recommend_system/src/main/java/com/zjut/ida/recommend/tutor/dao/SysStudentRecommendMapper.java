package com.zjut.ida.recommend.tutor.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjut.ida.recommend.tutor.core.entity.SysStudentRecommend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wly
 * @date 2021/5/13 15:07
 */
public interface SysStudentRecommendMapper extends BaseMapper<SysStudentRecommend> {
    /**
     * 按学生学号查询导师推荐列表
     *
     * @param studentId 学生学号
     * @param studySpeciality 查询条件
     * @return 导师 neo4j id 列表
     */
    List<Long> getTutorNeo4jIdListByStudentId(@Param("studentId") String studentId,
                                              @Param("studySpeciality") String studySpeciality);

    /**
     * 判断学生是否存在
     *
     * @param studentId 学生学号
     * @return 布尔
     */
    Boolean existStudent(String studentId);
}
