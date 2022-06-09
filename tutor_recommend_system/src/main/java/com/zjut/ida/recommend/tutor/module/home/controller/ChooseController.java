package com.zjut.ida.recommend.tutor.module.home.controller;

import com.alibaba.fastjson.JSONObject;
import com.zjut.ida.recommend.tutor.core.annotation.Privilege;
import com.zjut.ida.recommend.tutor.m2ndao.SysStudentDao;
import com.zjut.ida.recommend.tutor.module.dashboard.service.StudentService;
import com.zjut.ida.recommend.tutor.module.home.service.CFService;
import com.zjut.ida.recommend.tutor.utils.Response;
import com.zjut.ida.recommend.tutor.utils.enums.PrivilegeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("add")
    public JSONObject add(String studentId,Long tutorNeo4jId) {
        System.out.println("choose/add中的tutorNeo4jId为="+tutorNeo4jId+",studentId="+studentId);

        boolean res=studentDao.addHistory(studentId,tutorNeo4jId);
        System.out.println("choose/add中的res为="+res);
        return Response.bool(res);
    }
}
