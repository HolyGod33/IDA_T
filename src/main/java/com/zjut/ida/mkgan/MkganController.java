package com.zjut.ida.mkgan;

import com.zjut.ida.entity.Scholar;
import com.zjut.ida.service.ScholarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Casterx on 2022/4/18.
 */
@Controller
@RequestMapping(value = "/mkgan",method = RequestMethod.POST)
public class MkganController {

    @Autowired
    private MkganServiceImpl mkganService;

    @Autowired
    private ScholarService scholarService;

    @RequestMapping(value="/getRecommendList",method = RequestMethod.GET)
//    @ResponseBody
    public String getRecommendList(Model model,String studentId){
        List<Long> list=mkganService.requestMkganRecommendList(10,studentId);
        Map<String,List<Scholar>> map=new HashMap<>();
        model.addAttribute("tutor",scholarService.findScholarsById(list));
        return "TeacherRecommend";
    }
}
