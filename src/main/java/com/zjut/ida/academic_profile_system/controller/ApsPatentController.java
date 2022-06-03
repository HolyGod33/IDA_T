package com.zjut.ida.academic_profile_system.controller;

import com.zjut.ida.academic_profile_system.entity.Result;
import com.zjut.ida.academic_profile_system.service.ApsPatentService;
import com.zjut.ida.academic_profile_system.utils.ResultResponse;
import com.zjut.ida.entity.Patent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kokoryh on 2022/5/30.
 */
@Controller
@RequestMapping("/patent")
public class ApsPatentController {

    @Autowired
    private ApsPatentService patentService;

    // 根据scholar的id查询其专利及专利的所有作者信息
    @GetMapping("/findPatentsByScholarId")
    @ResponseBody
    public Result findPatentsByScholarId(@RequestParam Integer scholarId, Integer limit, String year) {
        List<Patent> patentList = patentService.findPatentsByScholarId(scholarId, limit, year);
        return ResultResponse.getSuccessResult(patentList);
    }

    // 根据scholar的id查询其专利的年份和年内的数量
    @GetMapping("/findPublishPatentCountByScholarId")
    @ResponseBody
    public Result findPublishPatentCountByScholarId(@RequestParam Integer scholarId) {
        List<Map<String, Object>> publishPatentCount = patentService.findPublishPatentCountByScholarId(scholarId);
        int maxCount = 10;
        for (Map<String, Object> item : publishPatentCount) {
            int count = Integer.parseInt(item.get("count").toString());
            if (count > maxCount) maxCount = count;
        }

        Map<String, Object> result = new HashMap<>();
        result.put("maxCount", maxCount);
        result.put("rows", publishPatentCount);

        return ResultResponse.getSuccessResult(result);
    }

}
