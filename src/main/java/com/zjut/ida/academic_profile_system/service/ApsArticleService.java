package com.zjut.ida.academic_profile_system.service;

import com.zjut.ida.entity.Article;

import java.util.List;
import java.util.Map;

/**
 * @author kokoryh on 2022/5/8
 */
public interface ApsArticleService {

    Article findArticleByArticleId(Integer articleId);

    List<Article> findArticlesByScholarId(Integer scholarId, String orderBy, Integer limit, String year);

    List<Map<String, Object>> findPublishArticleCountByScholarId(Integer scholarId);

}
