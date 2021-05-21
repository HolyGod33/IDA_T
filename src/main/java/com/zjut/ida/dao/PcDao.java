package com.zjut.ida.dao;

import com.zjut.ida.entity.Community;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Casterx on 2020/7/17.
 */
@Repository
public interface PcDao extends Neo4jRepository<List<Map<String, Object>>, Long> {

    //    @Query("match (n:Pcs)-[r:Pcl]-(n1:Pcs) where  ((n.organization=n1.organization={0}) or(n.affiliate=n1.affiliate={1})) return n as source,r as link,n1 as target limit 10")
//    @Query("match (n:Pcs)-[r:Pcl]-(n1:Pcs) where  n.organization=n1.organization={0} return n as source,r as link,n1 as target limit 5000")
    @Query("match (n:IdaCommunity) where n.name = {0} return n.community as community")
    String findCommunityName(String scholarName);

    @Query("match (n:IdaCommunity)-[r:ICR]-(n1:IdaCommunity) where n.community=n1.community={0}  return n as source,r as link,n1 as target")
    List<Map<String, Object>> findCommunity(String communityName);

    @Query("match (n:IdaCommunity) return distinct n.community as community")
     List<String> findAllCommunity();
}
