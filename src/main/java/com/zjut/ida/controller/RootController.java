package com.zjut.ida.controller;

import com.zjut.ida.entity.Scholar;
import com.zjut.ida.mkgan.MkganConstant;
import com.zjut.ida.mkgan.MkganServiceImpl;
import com.zjut.ida.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wly
 * @date 2021/4/22 10:42
 */
@Controller
public class RootController {


//    @Autowired
//    private MkganServiceImpl mkganService;
//
//    @Autowired
//    private ScholarService scholarService;
//
//
//    @Autowired
//    private ArticleDao articleDao;
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Autowired
//    private ScholarDao scholarDao;

//    /**
//     * 主页
//     */
//    @GetMapping("zpy")
//    public String zpy() {
//        return "TutorRecommendation_final";
//    }

//    @GetMapping("ScholarRecommend")
//    public String scholarRecommend(Model model, @RequestParam(value = "studentId", required = true) String studentId) {
//        List<Scholar> scholarList;
//        if(studentId==null || studentId.equals("null")){
//            scholarList=scholarDao.findScholarsByHistoryCount(10);
//        }else{
//            String studentRemapId=getStudentRemapId(studentId);
////            System.out.println("studentRemapId="+studentRemapId);
//            if(!studentRemapId.equals("null") && !studentRemapId.isEmpty()){
//                List<Long> list=mkganService.requestCKANRecommendList(10,studentId);
//                scholarList=scholarService.findScholarsById(list);
//            }else{
//                scholarList=scholarDao.findScholarsByHistoryCount(10);
//            }
//        }
//        model.addAttribute("scholar",scholarList);
//        return "recommend/ScholarRecommend";
//
//
//    }
//
//
//    @GetMapping("ArticleRecommend")
//    public String articleRecommend(Model model, @RequestParam(value = "studentId", required = true) String studentId) {
//
//        List<Article> articleList;
//        if(studentId==null || studentId.equals("null")){
//            articleList= articleDao.findArticlesByHistoryColdStart(10);
//        }else{
//            String studentRemapId=getStudentRemapId(studentId);
//            if(!studentRemapId.equals("null")  && !studentRemapId.isEmpty()){
//                List<Long> list=mkganService.requestMkganRecommendList(MkagnConstant.ARTICLE,10,studentId);
//                articleList= articleDao.findArticlesByHistory(list,10);
//            }else{
//                articleList= articleDao.findArticlesByHistoryColdStart(10);
//            }
//        }
//
//        model.addAttribute("article",articleList);
//        return "recommend/ArticleRecommend";
//
//    }

    @Autowired
    private MkganServiceImpl mkganService;

    @Autowired
    private ScholarService scholarService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private PatentService patentService;

    @Autowired
    private VerticalProjectService verticalProjectService;

    @Autowired
    private HorizontalProjectService horizontalProjectService;

    private int topN=10;


    @RequestMapping(value="/getRecommendList",method = RequestMethod.GET)
    public String getRecommendList(Model model,String studentId,String type){
        List<Long> list=mkganService.requestRecommendList(type,topN,studentId);
        switch(type){
            case MkganConstant.SCHOLAR:
                List<Scholar> scholarList=scholarService.findScholarsById(list);
                System.out.println("getRecommendList="+scholarList);
                model.addAttribute(type,scholarList);
                break;
            case MkganConstant.ARTICLE:
                model.addAttribute(type,articleService.findArticlesById(list));
                break;
            case MkganConstant.PATENT:
                model.addAttribute(type,patentService.findPatentsById(list));
                break;
            case MkganConstant.VP:
                model.addAttribute(type,verticalProjectService.findVerticalProjectsById(list));
                break;
            case MkganConstant.HP:
                model.addAttribute(type,horizontalProjectService.findHorizontalProjectsById(list));
                break;
            default:
                break;
        }
        System.out.println("type="+type);
        return "recommend/"+type+"Recommend";

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
