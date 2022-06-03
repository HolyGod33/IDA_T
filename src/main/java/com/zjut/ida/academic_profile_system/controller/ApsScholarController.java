package com.zjut.ida.academic_profile_system.controller;

import com.zjut.ida.academic_profile_system.entity.Result;
import com.zjut.ida.academic_profile_system.service.ApsDataService;
import com.zjut.ida.academic_profile_system.service.ApsScholarService;
import com.zjut.ida.academic_profile_system.utils.ResultResponse;
import com.zjut.ida.entity.Scholar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author kokoryh on 2022/5/10.
 */
@Controller
@RequestMapping("/scholar")
public class ApsScholarController {

    @Autowired
    private ApsScholarService scholarService;
    @Autowired
    private ApsDataService dataService;

    // 根据scholar的id查询其信息
    @GetMapping("/findScholarByScholarId")
    @ResponseBody
    public Result findScholarByScholarId(@RequestParam Integer scholarId) {
        Scholar scholar = scholarService.findScholarByScholarId(scholarId);
        return ResultResponse.getSuccessResult(scholar);
    }

    // 查询研究兴趣
    @GetMapping("/findInterestByScholarId")
    @ResponseBody
    public Result findInterestByScholarId(@RequestParam Integer scholarId) {
        List<List<Object>> interestList = dataService.findInterestByScholarId(scholarId);
        return ResultResponse.getSuccessResult(interestList);
    }

    // 查询关联教师
    @GetMapping("/findRelationByScholarId")
    @ResponseBody
    public Result findRelationByScholarId(@RequestParam Integer scholarId) {
        List<Object> relationList = dataService.findRelationByScholarId(scholarId);
        return ResultResponse.getSuccessResult(relationList);
    }

    // Co-Author
    @GetMapping("/findCoAuthorByScholarId")
    @ResponseBody
    public Result findCoAuthorByScholarId(@RequestParam Integer scholarId) {
        List<Object> coAuthorList = dataService.findCoAuthorByScholarId(scholarId);
        return ResultResponse.getSuccessResult(coAuthorList);
    }

    // Statistics
    @GetMapping("/findStatisticsByScholarId")
    @ResponseBody
    public Result findStatisticsByScholarId(@RequestParam Integer scholarId) {
        Map<String, Object> statisticsList = dataService.findStatisticsByScholarId(scholarId);
        return ResultResponse.getSuccessResult(statisticsList);
    }

    // 查询相似作者
    @GetMapping("/findSimilarScholarByScholarId")
    @ResponseBody
    public Result findSimilarScholarByScholarId(@RequestParam Integer scholarId) {
        List<Object> similarScholarList = dataService.findSimilarScholarByScholarId(scholarId);
        return ResultResponse.getSuccessResult(similarScholarList);
    }

}
