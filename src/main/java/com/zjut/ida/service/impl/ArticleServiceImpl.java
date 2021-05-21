package com.zjut.ida.service.impl;

import com.zjut.ida.dao.ArticleDao;
import com.zjut.ida.entity.Article;
import com.zjut.ida.entity.PublishArticleCount;
import com.zjut.ida.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

}
