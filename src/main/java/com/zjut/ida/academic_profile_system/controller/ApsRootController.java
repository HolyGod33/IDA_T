package com.zjut.ida.academic_profile_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kokoryh on 2022/5/31
 */
@Controller
@RequestMapping("/aps")
public class ApsRootController {

    @GetMapping("/**")
    public String APSPage() {
        return "aps";
    }

}
