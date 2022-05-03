package com.zjut.ida.controller;

import com.zjut.ida.entity.Scholar;
import com.zjut.ida.mkgan.MkganServiceImpl;
import com.zjut.ida.service.ScholarService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wly
 * @date 2021/4/22 10:42
 */
@Controller
public class RootController {


    @Autowired
    private MkganServiceImpl mkganService;

    @Autowired
    private ScholarService scholarService;


    /**
     * 主页
     */
    @GetMapping("zpy")
    public String zpy() {
        return "TutorRecommendation";
    }


    @GetMapping("TeacherRecommend")
    public String teacherRecommend(Model model) {
        List<Long> list=mkganService.requestMkganRecommendList(10);
        Map<String,List<Scholar>> map=new HashMap<>();
        model.addAttribute("tutor",scholarService.findScholarsById(list));
        return "recommend/TeacherRecommend";
    }

    /**
     * 登录页
     */
    @GetMapping("login")
    public String login(@RequestParam(value = "redirectMsg", required = false) String redirectMsg,
                        Model model) {
        if (StringUtils.isNotEmpty(redirectMsg)) {
            model.addAttribute("redirectMsg", redirectMsg);
        }
        return "login";
    }

    @GetMapping("login.html")
    public String loginHtml() {
        return "redirect:login";
    }

    /**
     * 注册页
     */
    @GetMapping("register")
    public String register() {
        return "register";
    }

    /**
     * 个人中心
     */
    @GetMapping("dashboard")
    public String dashboard() {
        return "dashboard";
    }

    /**
     * 相关导师页面
     */
    @GetMapping("relative")
    public String relative(@RequestParam("tutorNeo4jId") Long tutorNeo4jId,
                           Model model) {
        model.addAttribute("tutorNeo4jId", tutorNeo4jId);
        return "relative";
    }

    /**
     * 404 页面
     */
    @GetMapping("404")
    public String pageNotFound() {
        return "404";
    }
}
