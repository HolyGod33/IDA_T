package com.zjut.ida.recommend.tutor.module.home.controller;

import com.alibaba.fastjson.JSONObject;
import com.zjut.ida.recommend.tutor.core.annotation.Privilege;
import com.zjut.ida.recommend.tutor.m2ndao.SysStudentDao;
import com.zjut.ida.recommend.tutor.module.dashboard.service.StudentService;
import com.zjut.ida.recommend.tutor.module.home.service.CFService;
import com.zjut.ida.recommend.tutor.utils.Response;
import com.zjut.ida.recommend.tutor.utils.enums.PrivilegeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wly
 * @date 2021/6/3 16:26
 */
@RestController
@RequestMapping("choose")
public class ChooseController {
//    @Autowired
//    private CFService cfService;

    @Autowired
    private StudentService studentDao;


//    @PostMapping("add")
//    @Privilege(PrivilegeEnum.LoginPriv)
//    public JSONObject add(@RequestBody Long tutorNeo4jId) {
//        return Response.bool(cfService.addHistory(tutorNeo4jId));
//    }

    @PostMapping("add")
    public JSONObject add(@RequestBody Long tutorNeo4jId) {
        return Response.bool(studentDao.addHistory(tutorNeo4jId));
    }
}
