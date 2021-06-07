package com.zjut.ida.controller;

import com.zjut.ida.service.PcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Veloma on 2021/4/29.
 */

@Controller
@RequestMapping("/Community_wmk")
public class CommunityController_wmk {
    @Autowired
    private PcService pcService;

    @RequestMapping("")
    public String Community() {
        return "Community_wmk";
    }

    @ResponseBody
    @RequestMapping("/findAllCommunity")
    Object findCommunity() {
        return pcService.findAllCommunity();
    }

}
