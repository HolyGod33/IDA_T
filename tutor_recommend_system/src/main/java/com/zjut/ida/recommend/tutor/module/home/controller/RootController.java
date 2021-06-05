package com.zjut.ida.recommend.tutor.module.home.controller;

import com.zjut.ida.recommend.tutor.core.annotation.Privilege;
import com.zjut.ida.recommend.tutor.utils.enums.PrivilegeEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wly
 * @date 2021/4/22 10:42
 */
@Controller
public class RootController {

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
     * 主页
     */
    @Privilege(PrivilegeEnum.LoginPriv)
    @GetMapping("index")
    public String index() {
        return "index";
    }

    @Privilege(PrivilegeEnum.LoginPriv)
    @GetMapping("")
    public String root() {
        return "redirect:index";
    }

    @Privilege(PrivilegeEnum.LoginPriv)
    @GetMapping("index.html")
    public String html() {
        return "redirect:index";
    }

    /**
     * 个人中心
     */
    @Privilege(PrivilegeEnum.LoginPriv)
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
