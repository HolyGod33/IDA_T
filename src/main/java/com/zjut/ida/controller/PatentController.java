package com.zjut.ida.controller;

import com.zjut.ida.entity.Patent;
import com.zjut.ida.service.PatentService;
import com.zjut.ida.tool.ReturnMapTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author Casterx on 2019/10/27.
 */
@Controller
@RequestMapping("/patent")
public class PatentController {

    @Autowired
    private PatentService patentService;

    @RequestMapping("/findpatentbyscholarname")
    @ResponseBody
    public Map<String,Object> findPatentByScholarName(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                      @RequestParam(value = "size", defaultValue = "15") Integer size,
                                                      @RequestParam String scholarName){
        List<Patent> list=patentService.findPatentByScholarName(scholarName);
        return ReturnMapTool.returnMap(list,page,size);
    }


}