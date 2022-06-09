package com.zjut.ida.service;

import com.zjut.ida.entity.Article;
import com.zjut.ida.entity.PublishArticleCount;
import com.zjut.ida.entity.Scholar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Casterx on 2019/10/17.
 */
public interface ArticleService {

    List<Article> findArticlesByScholarName(String scholarName);

    List<Article> findArticlesByScholarNameAndYear(String scholarName, String year);

    List<Article> findArticlesById(List<Long> articleIdList);

    List<Article> findColdStartByHistoryCount(int topN);

}
