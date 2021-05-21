package com.zjut.ida.controller;

import com.zjut.ida.entity.VerticalProject;
import com.zjut.ida.service.VerticalProjectService;
import com.zjut.ida.tool.ReturnMapTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Casterx on 2019/10/23.
 */
@Controller
@RequestMapping("/verticalproject")
public class VerticalProjectController {

    @Autowired
    private VerticalProjectService verticalProjectService;

    @RequestMapping("/findverticalprojectbyscholarname")
    @ResponseBody
    public Map<String,Object> findVerticalProjectByScholarName(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                               @RequestParam(value = "size", defaultValue = "15") Integer size,
                                                               @RequestParam String scholarName){

        List<VerticalProject> list=verticalProjectService.findVerticalProjectByScholarName(scholarName);
        return ReturnMapTool.returnMap(list,page,size);
    }


}
