package com.zjut.ida.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Casterx on 2019/11/1.
 */
@Controller
@RequestMapping("/echars")
public class EChartsController {

    @GetMapping("/seecharts")
    public String seeCharts(){
        return "ECharts";
    }

}
