package com.zjut.ida.recommend.tutor.module.common.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zjut.ida.recommend.tutor.core.entity.SysDictClass;
import com.zjut.ida.recommend.tutor.core.m2nentity.NSysClass;
import com.zjut.ida.recommend.tutor.dao.SysDictClassMapper;
import com.zjut.ida.recommend.tutor.m2ndao.SysClassDao;
import com.zjut.ida.recommend.tutor.m2ndao.SysCollegeDao;
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
//    @Autowired
//    private SysDictClassMapper dictClassMapper;

    @Autowired
    private SysClassDao classDao;

    @Autowired
    private SysCollegeDao collegeDao;

//    public List<String> getClassDict(Integer admissionYear, String collegeName) {
//        List<SysDictClass> sysDictClasses = classDao.selectList(Wrappers.<SysDictClass>lambdaQuery()
//                .eq(SysDictClass::getAdmissionYear, admissionYear)
//                .eq(SysDictClass::getCollegeName, collegeName)
//                .orderByAsc(SysDictClass::getClassName));
//        return sysDictClasses.stream().map(SysDictClass::getClassName).collect(Collectors.toList());
//    }

    public List<String> getClassDict(Integer admissionYear, String collegeName) {
        List<NSysClass> sysDictClasses = classDao.findNSysClassByAdmissionYearAndCollegeNameOrderByClassName(admissionYear,collegeName);
        return sysDictClasses.stream().map(NSysClass::getClassName).collect(Collectors.toList());
    }

    public List<Integer> getAdmissionYearList() {
        return classDao.findUniqueAdmissionYearList();
    }

    public List<String> getCollegeDict() {
        return collegeDao.findCollegeNameList();
    }
}
