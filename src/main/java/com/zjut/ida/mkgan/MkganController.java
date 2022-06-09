package com.zjut.ida.mkgan;

import com.zjut.ida.dao.ArticleDao;
import com.zjut.ida.dao.ScholarDao;
import com.zjut.ida.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Casterx on 2022/4/18.
 */
@Controller
@RequestMapping(value = "/mkgan",method = RequestMethod.POST)
public class MkganController {


    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ScholarDao scholarDao;


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

    @RequestMapping("test")
    @ResponseBody
    public String getStr(){
        return "xzs";
    }



    @RequestMapping(value="/getRecommendList",method = RequestMethod.GET)
    public String getRecommendList(Model model,String studentId,String type){
        List<Long> list=mkganService.requestRecommendList(type,topN,studentId);
        switch(type){
            case MkganConstant.SCHOLAR:
                model.addAttribute(type,scholarService.findScholarsById(list));
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
        return "recommend/"+type+"Recommend";


    }



//    @GetMapping("ScholarRecommend")
//    public String scholarRecommend(Model model, @RequestParam(value = "studentId", required = true) String studentId) {
//        List<Scholar> scholarList;
//        if(studentId==null || studentId.equals("null")){
//            scholarList=scholarDao.findScholarsByHistoryCount(10);
//        }else{
//            String studentRemapId=getStudentRemapId(studentId);
////            System.out.println("studentRemapId="+studentRemapId);
//            if(!studentRemapId.equals("null") && !studentRemapId.isEmpty()){
//                List<Long> list=mkganService.requestCKANRecommendList("Scholar",10,studentId);
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

//    private String getStudentRemapId(String studentd) {
//        return String.valueOf(redisTemplate.opsForValue().get("user_"+studentd));
//    }
}
