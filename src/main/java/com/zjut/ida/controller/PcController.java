package com.zjut.ida.controller;

import com.zjut.ida.dao.PcDao;
import com.zjut.ida.entity.Pcs;
import com.zjut.ida.service.PcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Casterx on 2020/7/17.
 */
@Controller
@CrossOrigin //所有域名均可访问该类下所有接口
@RequestMapping("/pc")
public class PcController {


    @Autowired
    private PcService pcService;

    @ResponseBody
    @RequestMapping("/findCommunity")
    Object findCommunity(String scholarName){
//        System.out.println(scholarName);
        return pcService.findCommunity(scholarName);
    }

}
