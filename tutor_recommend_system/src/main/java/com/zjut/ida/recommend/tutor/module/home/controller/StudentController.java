package com.zjut.ida.recommend.tutor.module.home.controller;

import com.alibaba.fastjson.JSONObject;
import com.zjut.ida.recommend.tutor.module.home.dto.LoginDTO;
import com.zjut.ida.recommend.tutor.module.home.dto.RegisterDTO;
import com.zjut.ida.recommend.tutor.module.home.service.StudentService;
import com.zjut.ida.recommend.tutor.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wly
 * @date 2021/4/26 19:18
 */
@RestController("HomeStudentController")
@RequestMapping("student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("login")
    public JSONObject login(@RequestBody LoginDTO dto) {
        JSONObject result = studentService.login(dto.getStudentId(), dto.getPassword());
        return Response.ok(result);
    }

    @PostMapping("register")
    public JSONObject register(@RequestBody RegisterDTO dto) {
        return Response.bool(studentService.register(dto));
    }
}
