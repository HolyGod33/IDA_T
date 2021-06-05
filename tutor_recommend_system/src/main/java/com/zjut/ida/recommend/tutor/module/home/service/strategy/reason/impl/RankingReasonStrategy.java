package com.zjut.ida.recommend.tutor.module.home.service.strategy.reason.impl;

import com.alibaba.fastjson.JSONObject;
import com.zjut.ida.recommend.tutor.module.home.service.strategy.reason.IReasonStrategy;
import com.zjut.ida.recommend.tutor.utils.enums.RecommendStrategyType;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author wly
 * @date 2021/6/1 16:27
 */
@Component
public class RankingReasonStrategy implements IReasonStrategy {
    @Autowired
    private Driver driver;
    @Autowired
    private static final String CYPHER = "match (t:Teacher)--(a:Article)--(z:ZJUT_TOP100) " +
            "where id(t)=%d " +
            "with t, z, count(a) as count_a " +
            "return z.name as name, z.CN_Name as cn_name, z.ISSN as issn, count_a " +
            "order by count_a desc";


    @Override
    public JSONObject recommendReasonStrategy(Long neo4jId) {
        List<Map<String, Object>> list;
        try (Session session = driver.session()) {
            Result result = session.run(String.format(CYPHER, neo4jId));
            list = result.list(Record::asMap);
        }
        JSONObject json = new JSONObject();
        json.put("data", list);
        return json;
    }

    @Override
    public int getType() {
        return RecommendStrategyType.RANKING.ordinal();
    }
}
