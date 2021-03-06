package com.zjut.ida.service.impl;

import com.zjut.ida.dao.ArticleDao;
import com.zjut.ida.entity.Article;
import com.zjut.ida.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Casterx on 2019/10/17.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Override
    @Transactional(readOnly = true)
    public List<Article> findArticlesByScholarName(String scholarName) {
        List<Article> articleList=articleDao.findArticlesByScholarName(scholarName);
        for(Article article:articleList){
            if(article.getKeyWord()==null){
                article.setKeyWord(" ");
            }
            if(article.getCiteCount()==null){
                article.setCiteCount("0");
            }
        }
        return articleList;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Article> findArticlesByScholarNameAndYear(String scholarName, String year) {
        return articleDao.findArticlesByScholarNameAndYear(scholarName,year);
    }

    @Override
    public List<Article> findArticlesById(List<Long> articleIdList) {
        List<Article> articleList=new ArrayList<>();
        for(Long id:articleIdList){
            Article temp=articleDao.findArticlesById(id.intValue());
            articleList.add(temp);
        }
        return articleList;
    }

    @Override
    public List<Article> findColdStartByHistoryCount(int topN) {
        return articleDao.findColdStartByHistoryCount(topN);
    }
}
