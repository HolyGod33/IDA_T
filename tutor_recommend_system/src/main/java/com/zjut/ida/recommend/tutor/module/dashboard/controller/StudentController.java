package com.zjut.ida.recommend.tutor.module.dashboard.controller;

import com.alibaba.fastjson.JSONObject;
import com.zjut.ida.recommend.tutor.core.annotation.Privilege;
import com.zjut.ida.recommend.tutor.core.page.SimplePageInfo;
import com.zjut.ida.recommend.tutor.module.dashboard.service.StudentService;
import com.zjut.ida.recommend.tutor.module.dashboard.vo.StudentVO;
import com.zjut.ida.recommend.tutor.module.dashboard.vo.TutorVO;
import com.zjut.ida.recommend.tutor.module.home.dto.StudentDTO;
import com.zjut.ida.recommend.tutor.utils.Response;
import com.zjut.ida.recommend.tutor.utils.enums.PrivilegeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author wly
 * @date 2021/4/26 19:18
 */
@Privilege(PrivilegeEnum.LoginPriv)
@Controller("DashboardStudentController")
@RequestMapping("student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("info")
    public String info(Model model) {
        StudentVO studentVO = studentService.getInfo();
        model.addAttribute("student", studentVO);
        return "student/student_info";
    }

    @PutMapping("update")
    @ResponseBody
    public JSONObject update(@RequestBody StudentDTO dto) {
        return Response.bool(studentService.changeInfo(dto));
    }

    @PutMapping("updateStudySpecialityList")
    @ResponseBody
    public JSONObject updateStudySpecialityList(@RequestBody StudentDTO dto) {
        return Response.bool(studentService.changeStudySpecialityInfo(dto));
    }

    @GetMapping("history/list")
    public String historyList(@RequestParam("pageNum") int pageNum,
                              @RequestParam("pageSize") int pageSize,
                              Model model) {
        SimplePageInfo<TutorVO> pageInfo = studentService.getHistoryList(pageNum, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        return "student/student_history_list";
    }

    @GetMapping("favorite/list")
    public String favoriteList(@RequestParam("pageNum") int pageNum,
                               @RequestParam("pageSize") int pageSize,
                               Model model) {
        SimplePageInfo<TutorVO> pageInfo = studentService.getFavoriteList(pageNum, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        return "student/student_favorite_list";
    }
}
