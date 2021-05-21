package com.zjut.ida.controller;

import com.zjut.ida.entity.All;
import com.zjut.ida.entity.Scholar;
import com.zjut.ida.service.AllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Casterx on 2019/10/19.
 */
@RequestMapping("/all")
@Controller
public class AllController {

    @Autowired
    private AllService allService;

    @RequestMapping("findallbyanywords")
    public String findAllByAnyWords(@RequestParam("words") String words, Model model){
        System.out.println(words);
        All all= allService.findAllByAnyWords(words);
        model.addAttribute("all",all);
        return "All";
    }

    //测试用
    @RequestMapping("findallbyanywords1")
    @ResponseBody
    public All findAllByAnyWords1(@RequestParam("words") String words){
        System.out.println("findAllByAnyWords1="+words);
        return allService.findAllByAnyWords(words);
    }

}
