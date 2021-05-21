package com.zjut.ida.controller;

import com.zjut.ida.dao.ArticleDao;
import com.zjut.ida.entity.Article;
import com.zjut.ida.service.ArticleService;
import com.zjut.ida.tool.ReturnMapTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @author Casterx on 2019/10/17.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;



//    @GetMapping("/findarticlesbyscholarname")
//    @ResponseBody
//    public Page<Article> findArticlesByScholarName(@RequestParam(value = "page", defaultValue = "0") Integer page,
//                                                   @RequestParam(value = "size", defaultValue = "15") Integer size,
//                                                   @RequestParam String scholarName) {
//        Sort sort = new Sort(Sort.Direction.DESC, "a.year");
//        PageRequest pageRequest = PageRequest.of(page, size,sort);
//        return articleService.findArticlesByScholarName(scholarName, pageRequest);
//    }

    //Scholar页面——根据学者姓名查询论文，并按照论文所属期刊和目录的权重排列
    @GetMapping("/findarticlesbyscholarname")
    @ResponseBody
    public Map<String,Object> findArticlesByScholarName(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                             @RequestParam(value = "size", defaultValue = "15") Integer size,
                                             @RequestParam String scholarName) {
        List<Article> list=articleService.findArticlesByScholarName(scholarName);
        Map<String,Object> map=new HashMap();
        Collections.sort(list, new Comparator<Article>() {
            @Override
            public int compare(Article o1, Article o2) {
                int result=-1;
                if(o2.getJournal()!=null && o1.getJournal()!=null){
                    result=(o2.getJournal().getWeight()+o2.getJournal().getCatalog().getWeight())-
                            (o1.getJournal().getWeight()+o1.getJournal().getCatalog().getWeight());
                }
                return result;
            }
        });
        return ReturnMapTool.returnMap(list,page,size);
    }


    //Scholar页面——根据发表年份选择出论文
    @GetMapping("/findarticlesbyscholarnameandyear")
    @ResponseBody
    public Map<String,Object> findArticlesByScholarNameAndYear(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                    @RequestParam(value = "size", defaultValue = "15") Integer size,
                                                    @RequestParam(value = "scholarName", defaultValue = "张元鸣") String scholarName,
                                                    @RequestParam String year) {
//        Map<String,Object> map=new HashMap();
        List<Article> list=articleService.findArticlesByScholarNameAndYear(scholarName, year);
//        map.put("list",articleList);
//        int allSize=articleList.size();//查询到的总的论文数量
//        int index=page*size;//分页的首页位置
//        int last=page*size+size;//分页的末尾位置
//        int allPage=allSize/size;//总共分出的页数
//        map.put("allPage",allPage);
//        map.put("hasNext",page<allPage);
//        map.put("isFirst",page==0);
        return ReturnMapTool.returnMap(list,page,size);
    }
}
