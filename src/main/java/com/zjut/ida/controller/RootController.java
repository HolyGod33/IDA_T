package com.zjut.ida.controller;

import com.zjut.ida.dao.ArticleDao;
import com.zjut.ida.dao.ScholarDao;
import com.zjut.ida.entity.Article;
import com.zjut.ida.entity.Scholar;
import com.zjut.ida.mkgan.MkganServiceImpl;
import com.zjut.ida.service.ArticleService;
import com.zjut.ida.service.ScholarService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wly
 * @date 2021/4/22 10:42
 */
@Controller
public class RootController {


    @Autowired
    private MkganServiceImpl mkganService;

    @Autowired
    private ScholarService scholarService;


    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ScholarDao scholarDao;

//    /**
//     * 主页
//     */
//    @GetMapping("zpy")
//    public String zpy() {
//        return "TutorRecommendation_final";
//    }



    @GetMapping("TeacherRecommend")
    public String teacherRecommend(Model model,@RequestParam(value = "studentId", required = true) String studentId) {
        List<Scholar> scholarList;
        if(studentId==null || studentId.equals("null")){
            scholarList=scholarDao.findScholarsByHistoryCount(10);
        }else{
            String studentRemapId=getStudentRemapId(studentId);
            System.out.println("studentRemapId="+studentRemapId);
            if(!studentRemapId.equals("null") && !studentRemapId.isEmpty()){
                List<Long> list=mkganService.requestMkganRecommendList(10,studentId);
                scholarList=scholarService.findScholarsById(list);
            }else{
                scholarList=scholarDao.findScholarsByHistoryCount(10);
            }
        }
        model.addAttribute("tutor",scholarList);


//        List<Scholar> scholarList=new ArrayList<>();
//        String[] strs=new String[]{"A","B","C","D","E","F","G","H","I","J"};
//        String[] names=new String[]{"刘一","陈二","张三","李四","王五","刘钊","孙棋","周拔","吴九","郑石"};
//
//        for(int i=0;i<10;i++){
//            scholarList.add(new Scholar(names[i],"学院"+strs[i],"方向"+strs[i],"邮箱"+strs[i]));
//        }
//        model.addAttribute("tutor",scholarList);
        return "recommend/TeacherRecommend";


    }

    @GetMapping("PaperRecommend")
    public String paperRecommend(Model model,@RequestParam(value = "studentId", required = true) String studentId) {

        List<Article> articleList;
        if(studentId==null || studentId.equals("null")){
            articleList= articleDao.findArticlesByHistoryColdStart(10);
        }else{
            String studentRemapId=getStudentRemapId(studentId);
            if(!studentRemapId.equals("null")  && !studentRemapId.isEmpty()){
                List<Long> list=mkganService.requestMkganRecommendList(10,studentId);
                articleList= articleDao.findArticlesByHistory(list,10);
            }else{
                articleList= articleDao.findArticlesByHistoryColdStart(10);
            }
        }

        model.addAttribute("papers",articleList);
        return "recommend/PaperRecommend";

    }

    private String getStudentRemapId(String studentID) {
        return String.valueOf(redisTemplate.opsForValue().get(studentID));
    }


    /**
     * 登录页
     */
    @GetMapping("login")
    public String login(@RequestParam(value = "redirectMsg", required = false) String redirectMsg,
                        Model model) {
        if (StringUtils.isNotEmpty(redirectMsg)) {
            model.addAttribute("redirectMsg", redirectMsg);
        }
        return "login";
    }

    @GetMapping("login.html")
    public String loginHtml() {
        return "redirect:login";
    }

    /**
     * 注册页
     */
    @GetMapping("register")
    public String register() {
        return "register";
    }

    /**
     * 个人中心
     */
    @GetMapping("dashboard")
    public String dashboard() {
        return "dashboard";
    }

    /**
     * 相关导师页面
     */
    @GetMapping("relative")
    public String relative(@RequestParam("tutorNeo4jId") Long tutorNeo4jId,
                           Model model) {
        model.addAttribute("tutorNeo4jId", tutorNeo4jId);
        return "relative";
    }

    /**
     * 404 页面
     */
    @GetMapping("404")
    public String pageNotFound() {
        return "404";
    }
}
