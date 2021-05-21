package com.zjut.ida.service;

import com.zjut.ida.entity.All;
import com.zjut.ida.entity.Article;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Casterx on 2019/10/19.
 */
public interface AllService {

    All findAllByAnyWords(String words);
}
