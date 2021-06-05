package com.zjut.ida.recommend.tutor.module.home.service.strategy.label.impl;

import com.zjut.ida.recommend.tutor.config.SysStudentHolder;
import com.zjut.ida.recommend.tutor.dao.SysStudentRecommendMapper;
import com.zjut.ida.recommend.tutor.module.home.service.strategy.label.ILabelStrategy;
import com.zjut.ida.recommend.tutor.module.home.service.strategy.recommend.impl.KgatModelRecommendStrategy;
import com.zjut.ida.recommend.tutor.utils.SortUtils;
import com.zjut.ida.recommend.tutor.utils.enums.RecommendStrategyType;
import org.apache.commons.lang3.StringUtils;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wly
 * @date 2021/6/2 14:13
 */
@Component
public class KgatModelLabelStrategy implements ILabelStrategy {
    @Autowired
    private Driver driver;
    @Autowired
    private SysStudentHolder studentHolder;

    @Autowired
    private SysStudentRecommendMapper recommendMapper;

    @Autowired
    private KgatModelRecommendStrategy recommendStrategy;

    private static final String RECOMMEND_LABEL_CYPHER =
            "match (n) where id(n) in [%s] " +
                    "with n.study_speciality as label " +
                    "where label is not null " +
                    "return label";

    @Override
    public List<String> labelStrategy() {
        String studentId = studentHolder.getStudent().getStudentId();

        // 若表中没有数据，则插入
        recommendStrategy.insertRecommendListIfAbsent(studentId);

        // 获取全部要推荐的导师的 neo4j id
        List<Long> neo4jIdList = recommendMapper.getTutorNeo4jIdListByStudentId(studentId, "");

        // 获取标签信息
        try (Session session = driver.session()) {
            Result result = session.run(String.format(RECOMMEND_LABEL_CYPHER, StringUtils.join(neo4jIdList, ",")));
            List<String> labels = result.list(record -> record.get("label").asString());
            if (CollectionUtils.isEmpty(labels)) {
                return new ArrayList<>();
            }

            // 计数
            Map<String, Integer> labelMap = new HashMap<>();
            labels.forEach(label -> labelMap.merge(label, 1, Integer::sum));
            // 排序
            return SortUtils.sortByValue(labelMap, LABEL_MAX_NUM, false);
        }
    }

    @Override
    public int getType() {
        return RecommendStrategyType.KGAT.ordinal();
    }
}
