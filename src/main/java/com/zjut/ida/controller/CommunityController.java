package com.zjut.ida.controller;

import com.zjut.ida.service.AllService;
import com.zjut.ida.service.PcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Veloma on 2021/4/29.
 */

@Controller
@RequestMapping("/Community")
public class CommunityController {
    @Autowired
    private PcService pcService;

    @RequestMapping("")
    public String Community() {
        return "Community";
    }

    @ResponseBody
    @RequestMapping("/findAllCommunity")
    Object findCommunity() {
        return pcService.findAllCommunity();
    }

}
