package com.zjut.ida.service;

import com.zjut.ida.entity.HorizontalProject;
import com.zjut.ida.entity.Partner;
import com.zjut.ida.entity.PublishArticleCount;
import com.zjut.ida.entity.Scholar;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Casterx on 2019/10/17.
 */
public interface ScholarService {


    List<Scholar> findScholarsByNameContains(String scholarName);

    List<Partner> findPartnersByScholarName(String scholarName);

    List<PublishArticleCount> findPublishArticleCountByScholarName(String scholarName);
    Object findPublishArticleCountByScholarName1(String scholarName);

    List<String> findKeyWordsByScholarName(String scholarName);
    Object findKeyWordsByScholarName1(String scholarName);

    List<Partner> findPartnersByScholarNameForCount(String scholarName, Integer count);

    Object findPartnersByScholarNameForCount1(String scholarName, Integer count);

    List<Scholar> findScholarsById(List<Long> scholarIdList);

//    List<Integer> findScholarHistoryCount(List<Long> scholarIdList);

    List<Map<String,Object>> findHistoryCountByScholarIdList(List<Long> scholarIdList);

    List<Map<String,Object>> findColdStartByHistoryCount(int topN);

    List<Map<String,Object>> findStudentByScholarId(Long scholarId);
}
