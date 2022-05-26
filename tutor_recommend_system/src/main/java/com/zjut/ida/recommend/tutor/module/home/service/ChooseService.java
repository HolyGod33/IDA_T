package com.zjut.ida.recommend.tutor.module.home.service;

import com.zjut.ida.recommend.tutor.config.SysStudentHolder;
import com.zjut.ida.recommend.tutor.core.entity.SysCF;
import com.zjut.ida.recommend.tutor.dao.SysCFMapper;
import com.zjut.ida.recommend.tutor.m2ndao.SysStudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wly
 * @date 2021/6/3 16:30
 */
@Service
public class ChooseService {
    @Autowired
    private TutorService tutorService;
    @Autowired
    private StudentService studentService;

    @Autowired
    private SysStudentHolder studentHolder;

    @Autowired
    private SysCFMapper cfMapper;


    @Autowired
    private SysStudentDao studentDao;


    public boolean addHistory(Long tutorNeo4jId) {
//        Long tutorRemapId = tutorService.getRemapIdByNeo4jId(tutorNeo4jId);
//        Long studentRemapId = studentService.getRemapIdByStudentId(studentHolder.getStudentId());

//        SysCF sysCF = new SysCF();
//        sysCF.setStudentId(studentHolder.getStudentId());
//        sysCF.setStudentRemapId(studentRemapId);
//        sysCF.setTutorNeo4jId(tutorNeo4jId);
//        sysCF.setTutorRemapId(tutorRemapId);

//        cfMapper.insert(sysCF);
        studentDao.saveHistory(studentHolder.getStudentId(),tutorNeo4jId);
        return true;
    }
}
