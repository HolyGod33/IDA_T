package com.zjut.ida.service;

import com.zjut.ida.entity.Article;
import com.zjut.ida.entity.Patent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @author Casterx on 2019/10/27.
 */
public interface PatentService {

    List<Patent> findPatentByScholarName(String scholarName);

    List<Patent> findPatentsById(List<Long> patentIdList);

    List<Patent> findColdStartByHistoryCount(int topN);
}
