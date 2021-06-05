package com.zjut.ida.recommend.tutor.module.common.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zjut.ida.recommend.tutor.core.entity.SysDictClass;
import com.zjut.ida.recommend.tutor.dao.SysDictClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wly
 * @date 2021/5/18 20:50
 */
@Service
public class DictService {
    @Autowired
    private SysDictClassMapper dictClassMapper;

    public List<String> getClassDict(Integer admissionYear, String collegeName) {
        List<SysDictClass> sysDictClasses = dictClassMapper.selectList(Wrappers.<SysDictClass>lambdaQuery()
                .eq(SysDictClass::getAdmissionYear, admissionYear)
                .eq(SysDictClass::getCollegeName, collegeName)
                .orderByAsc(SysDictClass::getClassName));
        return sysDictClasses.stream().map(SysDictClass::getClassName).collect(Collectors.toList());
    }

    public List<Integer> getAdmissionYearList() {
        return dictClassMapper.findUniqueAdmissionYearList();
    }

    public List<String> getCollegeDict() {
        return dictClassMapper.findCollegeNameList();
    }
}
