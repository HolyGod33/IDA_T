package com.zjut.ida.controller;


import com.zjut.ida.entity.Achievement;
import com.zjut.ida.entity.Scholar;
import com.zjut.ida.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Veloma on 2020/10/21.
 */

@Controller
@RequestMapping("/achievement")
public class AchievementController {

    @Autowired
    private AchievementService achievementService;

    @RequestMapping("/findachievementbyname")
    @ResponseBody
    public Achievement findAchievementByAnyName(@RequestParam Long id1, @RequestParam Long id2, Model model) {
        Scholar scholar1 = achievementService.findScholarByScholarId(id1);
        Scholar scholar2 = achievementService.findScholarByScholarId(id2);
        Achievement achievement = achievementService.findAchievementByName(scholar1.getName(), scholar2.getName());
        model.addAttribute("achievement", achievement);
        return achievement;
    }

    //测试用
    public Achievement findAchievementByAnyName1(Long id1, Long id2) {
        Scholar scholar1 = achievementService.findScholarByScholarId(id1);
        Scholar scholar2 = achievementService.findScholarByScholarId(id2);
        Achievement achievement = achievementService.findAchievementByName(scholar1.getName(), scholar2.getName());
        return achievement;
    }


}
