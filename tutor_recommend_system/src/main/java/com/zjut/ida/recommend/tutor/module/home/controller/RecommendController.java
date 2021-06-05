package com.zjut.ida.recommend.tutor.module.home.controller;

import com.alibaba.fastjson.JSONObject;
import com.zjut.ida.recommend.tutor.module.home.service.RecommendService;
import com.zjut.ida.recommend.tutor.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author wly
 * @date 2021/4/27 20:23
 */
@Controller
@RequestMapping("recommend")
public class RecommendController {
    @Autowired
    private RecommendService recommendService;

    @GetMapping("search")
    public String search(@RequestParam("pageNum") Integer pageNum,
                         @RequestParam("pageSize") Integer pageSize,
                         @RequestParam(value = "studySpeciality", defaultValue = "") String studySpeciality,
                         Model model) {
        model.addAttribute("pageInfo", recommendService.getRecommendList(pageNum, pageSize, studySpeciality));
        model.addAttribute("isTrain", recommendService.isTrain());
        return "student/student_recommend_list";
    }

    @GetMapping("relative")
    public String relative(@RequestParam("tutorNeo4jId") Long tutorNeo4jId,
                           Model model) {
        model.addAttribute("relativeList", recommendService.getRelativeList(tutorNeo4jId));
        return "tutor/tutor_relative_list";
    }

    @GetMapping("reason")
    @ResponseBody
    public JSONObject reason(@RequestParam("neo4jId") Long neo4jId) {
        JSONObject result = recommendService.getRecommendReason(neo4jId);
        return Response.ok(result);
    }

    @GetMapping("label/list")
    public String labelList(Model model) {
        List<String> labelList = recommendService.getStudySpecialityList();
        model.addAttribute("labelList", labelList);
        return "tutor/tutor_study_speciality_list";
    }
}
