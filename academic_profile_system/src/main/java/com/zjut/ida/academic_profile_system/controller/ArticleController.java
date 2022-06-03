package com.zjut.ida.academic_profile_system.controller;

import com.zjut.ida.academic_profile_system.entity.Article;
import com.zjut.ida.academic_profile_system.entity.Result;
import com.zjut.ida.academic_profile_system.service.ArticleService;
import com.zjut.ida.academic_profile_system.utils.ResultResponse;
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
 * @author kokoryh on 2022/5/8.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    // 根据article的id查询其信息及作者信息
    @GetMapping("/findArticleByArticleId")
    @ResponseBody
    public Result findArticleByArticleId(@RequestParam Integer articleId) {
        Article article = articleService.findArticleByArticleId(articleId);
        return ResultResponse.getSuccessResult(article);
    }

    // 根据scholar的id查询其发表的论文及论文的所有作者信息
    @GetMapping("/findArticlesByScholarId")
    @ResponseBody
    public Result findArticlesByScholarId(@RequestParam Integer scholarId, @RequestParam String orderBy, Integer limit, String year) {
        List<Article> articleList = articleService.findArticlesByScholarId(scholarId, orderBy, limit, year);
        return ResultResponse.getSuccessResult(articleList);
    }

    // 根据scholar的id查询其发表论文的年份和年内的数量
    @GetMapping("/findPublishArticleCountByScholarId")
    @ResponseBody
    public Result findPublishArticleCountByScholarId(@RequestParam Integer scholarId) {
        List<Map<String, Object>> publishArticleCount = articleService.findPublishArticleCountByScholarId(scholarId);
        int maxCount = 10;
        for (Map<String, Object> item : publishArticleCount) {
            int count = Integer.parseInt(item.get("count").toString());
            if (count > maxCount) maxCount = count;
        }

        Map<String, Object> result = new HashMap<>();
        result.put("maxCount", maxCount);
        result.put("rows", publishArticleCount);

        return ResultResponse.getSuccessResult(result);
    }

}
