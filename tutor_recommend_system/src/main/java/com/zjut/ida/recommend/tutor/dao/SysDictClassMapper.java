package com.zjut.ida.recommend.tutor.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjut.ida.recommend.tutor.core.entity.SysDictClass;

import java.util.List;

/**
 * 班级字典表 Mapper 接口
 *
 * @author wly
 * @date 2021/5/18 20:50
 */
public interface SysDictClassMapper extends BaseMapper<SysDictClass> {

    List<Integer> findUniqueAdmissionYearList();

    List<String> findCollegeNameList();
}
