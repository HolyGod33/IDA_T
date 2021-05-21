package com.zjut.ida.service;

import com.zjut.ida.entity.Article;
import com.zjut.ida.entity.PublishArticleCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Casterx on 2019/10/17.
 */
public interface ArticleService {

    List<Article> findArticlesByScholarName(String scholarName);

    List<Article> findArticlesByScholarNameAndYear(String scholarName, String year);

}
