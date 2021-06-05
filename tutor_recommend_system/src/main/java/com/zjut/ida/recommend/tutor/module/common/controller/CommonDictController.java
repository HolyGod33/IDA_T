package com.zjut.ida.recommend.tutor.module.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.zjut.ida.recommend.tutor.module.common.service.DictService;
import com.zjut.ida.recommend.tutor.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wly
 * @date 2021/5/18 20:49
 */
@RestController
@RequestMapping("dict")
public class CommonDictController {
    @Autowired
    private DictService dictService;

    @GetMapping("class/search")
    public JSONObject classSearch(@RequestParam("admissionYear") Integer admissionYear,
                                  @RequestParam("collegeName") String collegeName) {
        return Response.ok(dictService.getClassDict(admissionYear, collegeName));
    }

    @GetMapping("admission/list")
    public JSONObject admissionYearList() {
        return Response.ok(dictService.getAdmissionYearList());
    }

    @GetMapping("college/list")
    public JSONObject collegeList() {
        return Response.ok(dictService.getCollegeDict());
    }
}
