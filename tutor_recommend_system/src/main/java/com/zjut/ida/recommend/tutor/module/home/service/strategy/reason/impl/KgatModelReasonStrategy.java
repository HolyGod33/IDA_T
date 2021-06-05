package com.zjut.ida.recommend.tutor.module.home.service.strategy.reason.impl;

import com.alibaba.fastjson.JSONObject;
import com.zjut.ida.recommend.tutor.config.SysStudentHolder;
import com.zjut.ida.recommend.tutor.dao.SysCFMapper;
import com.zjut.ida.recommend.tutor.module.home.service.strategy.reason.IReasonStrategy;
import com.zjut.ida.recommend.tutor.module.home.vo.RecommendReasonDataVO;
import com.zjut.ida.recommend.tutor.module.home.vo.RecommendReasonLinkVO;
import com.zjut.ida.recommend.tutor.utils.enums.RecommendStrategyType;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Value;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wly
 * @date 2021/6/2 13:53
 */
@Component
public class KgatModelReasonStrategy implements IReasonStrategy {
    @Autowired
    private Driver driver;
    @Autowired
    private SysStudentHolder studentHolder;
    @Autowired
    private SysCFMapper cfMapper;

    private static final List<String> ENTITY_TYPE = Arrays.stream(
                    new String[]{"Article", "Patent", "VerticalProject", "HorizontalProject", "Organization", "Teacher"})
                    .collect(Collectors.toList());
    private static final String CYPHER =
            "match path=(t1:Teacher)-[*1]-(:%s)-[*1]-(t2:Teacher) " +
                    "where id(t1) = %d and id(t2) = %d " +
                    "return path";

    @Override
    public JSONObject recommendReasonStrategy(Long neo4jId) {
        String studentId = studentHolder.getStudent().getStudentId();
        List<Long> cfNeo4jIdList = cfMapper.findTutorNeo4jIdListByStudentId(studentId);

        try (Session session = driver.session()) {
            // 遍历实体类型
            for (String entityType : ENTITY_TYPE) {
                // 遍历历史记录
                for (Long cfNeo4jId : cfNeo4jIdList) {
                    // 获取路径
                    Result result = session.run(String.format(CYPHER, entityType, neo4jId, cfNeo4jId));
                    List<Path> path = result.list(record -> record.get("path").asPath());

                    // 构造 echarts 数据
                    if (path.size() > 0 && path.get(0).relationships().iterator().hasNext()) {
                        Map<Long, Node> nodeMap = new HashMap<>();
                        path.get(0).nodes().forEach(node -> {
                            nodeMap.put(node.id(), node);
                        });
                        List<RecommendReasonLinkVO> linkList = new ArrayList<>();
                        Set<RecommendReasonDataVO> dataSet = new HashSet<>();

                        path.get(0).relationships().forEach(relationship -> {
                            Node startNode = nodeMap.get(relationship.startNodeId());
                            Node endNode = nodeMap.get(relationship.endNodeId());

                            RecommendReasonLinkVO link = new RecommendReasonLinkVO(
                                    getNodeName(startNode),
                                    getNodeName(endNode),
                                    relationship.type());
                            linkList.add(link);

                            Iterator<String> startNodeIter = startNode.labels().iterator();
                            int startNodeLabel = -1;
                            while (startNodeIter.hasNext()) {
                                startNodeLabel = ENTITY_TYPE.indexOf(startNodeIter.next());
                                if (startNodeLabel != -1) {
                                    break;
                                }
                            }
                            Iterator<String> endNodeIter = endNode.labels().iterator();
                            int endNodeLabel = -1;
                            while (endNodeIter.hasNext()) {
                                endNodeLabel = ENTITY_TYPE.indexOf(endNodeIter.next());
                                if (endNodeLabel != -1) {
                                    break;
                                }
                            }
                            RecommendReasonDataVO data1 = new RecommendReasonDataVO(
                                    getNodeName(startNode),
                                    startNodeLabel);
                            RecommendReasonDataVO data2 = new RecommendReasonDataVO(
                                    getNodeName(endNode),
                                    endNodeLabel);
                            dataSet.add(data1);
                            dataSet.add(data2);
                        });

                        JSONObject json = new JSONObject();
                        json.put("data", new ArrayList<>(dataSet));
                        json.put("links", linkList);

                        return json;
                    }
                }
            }
        }

        return null;
    }

    /**
     * 获取节点的名称
     * Article实体的名称在 title 属性
     *
     * @param node 节点
     * @return 节点名
     */
    private String getNodeName(Node node) {
        Value name = node.get("name");
        if (name.isNull()) {
            return node.get("title").asString();
        } else {
            return name.asString();
        }
    }

    @Override
    public int getType() {
        return RecommendStrategyType.KGAT.ordinal();
    }
}
