package com.zjut.ida.recommend.tutor.module.home.service.strategy.label.impl;

import com.github.pagehelper.PageHelper;
import com.zjut.ida.recommend.tutor.config.SysStudentHolder;
import com.zjut.ida.recommend.tutor.dao.SysCFMapper;
import com.zjut.ida.recommend.tutor.module.home.service.strategy.label.ILabelStrategy;
import com.zjut.ida.recommend.tutor.utils.SortUtils;
import com.zjut.ida.recommend.tutor.utils.enums.RecommendStrategyType;
import org.apache.commons.lang3.StringUtils;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wly
 * @date 2021/6/1 16:27
 */
@Component
public class RankingHistoryLabelStrategy implements ILabelStrategy {
    @Autowired
    private Driver driver;
    @Autowired
    private SysStudentHolder studentHolder;
    @Autowired
    private SysCFMapper cfMapper;

    private static final String CYPHER = "match (n:Teacher) " +
            "where id(n) in [%s] " +
            "return n.study_speciality as study_speciality";

    @Override
    public List<String> labelStrategy() {
        PageHelper.startPage(1, 50, false);
        List<Long> tutorNeo4jIdList = cfMapper.findTutorNeo4jIdListByStudentId(studentHolder.getStudentId());
        List<String> studySpecialityList;
        try (Session session = driver.session()) {
            Result result = session.run(String.format(CYPHER, StringUtils.join(tutorNeo4jIdList, ",")));
            studySpecialityList = result.list(record -> record.get("study_speciality").asString());
        }
        // 计数
        Map<String, Integer> labelMap = new HashMap<>();
        studySpecialityList.forEach(studySpeciality -> labelMap.merge(studySpeciality, 1, Integer::sum));
        // 排序
        return SortUtils.sortByValue(labelMap, LABEL_MAX_NUM, false);
    }

    @Override
    public int getType() {
        return RecommendStrategyType.RANKING_HISTORY.ordinal();
    }
}
