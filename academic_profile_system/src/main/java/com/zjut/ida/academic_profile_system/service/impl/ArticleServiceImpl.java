package com.zjut.ida.academic_profile_system.service.impl;

import com.zjut.ida.academic_profile_system.dao.ArticleDao;
import com.zjut.ida.academic_profile_system.entity.Article;
import com.zjut.ida.academic_profile_system.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author kokoryh on 2022/5/8
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Override
    @Transactional(readOnly = true)
    public Article findArticleByArticleId(Integer articleId) {
        Article article = articleDao.findArticleByArticleId(articleId);
        if (article.getKeyWord() == null) {
            article.setKeyWord("");
        }
        if (article.getCiteCount() == null) {
            article.setCiteCount("0");
        }
        return article;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Article> findArticlesByScholarId(Integer scholarId, String orderBy, Integer limit, String year) {
        String base1 = "match (s1:Scholar)-[]-(a1:Article) where id(s1)=" + scholarId;
        String y = "";
        String o;
        String l = "";
        String base2 = " match p=(a1)-[]-(s2:Scholar) return p";
        if (!Objects.equals(year, "")) {
            y = " and a1.year='" + year + "'";
        }
        if (Objects.equals(orderBy, "year")) {
            o = " and a1.year is not null with a1 order by a1.year desc";
        } else {
            o = " and a1.citeCount is not null with a1 order by toInt(a1.citeCount) desc";
        }
        if (limit != 0) {
            l = " limit " + limit;
        }
        String cql = base1 + y + o + l + base2;

        List<Article> articleList = articleDao.findArticlesByScholarId(cql);
        for (Article article : articleList) {
            if (article.getKeyWord() == null) {
                article.setKeyWord("");
            }
            if (article.getCiteCount() == null) {
                article.setCiteCount("0");
            }
        }
        return articleList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> findPublishArticleCountByScholarId(Integer scholarId) {
        List<Map<String, Object>> publishArticleCount = articleDao.findPublishArticleCountByScholarId(scholarId);
//        publishArticleCount.removeIf(item -> item.get("year") == null);
        return publishArticleCount;
    }

}
