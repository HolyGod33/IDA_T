package com.zjut.ida.achievement_recommend_system;

import org.neo4j.driver.v1.*;

import java.util.ArrayList;
import java.util.List;

import static org.neo4j.driver.v1.Values.parameters;

public class UserHistoryService {
    //返回历史信息列表
    public static List<ArticleBean> HistoryList(int user, List<Integer> itemList) {
        List <ArticleBean> list = new ArrayList<ArticleBean>();
        for(int i = 0; i < itemList.size(); i++){
            ArticleBean article = new ArticleBean();
            list.add(article);
        }

        //连接neo4j
        Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "0924wzd."));
        Session session = driver.session();
        String sql1 = "MATCH (a:Article) WHERE id(a) = {id}" +
                "RETURN id(a) AS id, a.title AS title, a.partner AS partner," +
                "a.citeCount AS citeCount, a.year AS year, a.keyWord AS keyword";

        String sql2 = "MATCH (p:Patent) WHERE id(p) = {id}" +
                "RETURN id(p) AS id, p.name AS title, p.inventors AS partner," +
                "p.applyDate AS year, p.type AS type, p.state AS state," +
                "p.applicant AS applicant, p.organization AS organization";

        String sql3 = "MATCH (h:HorizontalProject) WHERE id(h) = {id}" +
                "RETURN id(h) AS id, h.name AS title, h.leader AS partner," +
                "h.approvalDate AS year, h.nature AS nature, h.area AS area," +
                "h.boss AS boss, h.planedMoney AS planedMoney, h.organization AS organization";

        for(int i = 0; i < itemList.size(); i++) {
            StatementResult result = null;
            //查询Article
            if(itemList.get(i) <= 442498) {
                result = session.run(sql1, parameters("id", itemList.get(i)));
            }
            //查询Patent
            else if((itemList.get(i) >= 443550 && itemList.get(i) <= 443556) || (itemList.get(i) >= 445482 && itemList.get(i) <= 445498) ||
                    (itemList.get(i) >= 447633 && itemList.get(i) <= 447638) || (itemList.get(i) >= 450042 && itemList.get(i) <= 450058) ||
                    (itemList.get(i) >= 453731 && itemList.get(i) <= 453738) || (itemList.get(i) >= 455794 && itemList.get(i) <= 455798) ||
                    (itemList.get(i) >= 457767 && itemList.get(i) <= 457778) || (itemList.get(i) >= 459426 && itemList.get(i) <= 459438) ||
                    (itemList.get(i) >= 461113 && itemList.get(i) <= 461117) || (itemList.get(i) >= 462390 && itemList.get(i) <= 462398) ||
                    (itemList.get(i) >= 463949)) {
                result = session.run(sql2, parameters("id", itemList.get(i)));
            }

            else {
                result = session.run(sql3, parameters("id", itemList.get(i)));

            }

            while ( result.hasNext() )
            {
                Record record = result.next();
                list.get(i).setId(itemList.get(i));
                list.get(i).setTitle(record.get( "title" ).asString());
                list.get(i).setPartner(record.get("partner").asString());
//                    System.out.println(record.get("title").asString());
                list.get(i).setCiteCount(record.get("citeCount").asString());
                list.get(i).setYear(record.get("year").asString());
                list.get(i).setKeyword(record.get("keyword").asString());

                list.get(i).setType(record.get("type").asString());
                list.get(i).setState(record.get("state").asString());
                list.get(i).setApplicant(record.get("applicant").asString());
                list.get(i).setOrganization(record.get("organization").asString());

                list.get(i).setNature(record.get("nature").asString());
                list.get(i).setArea(record.get("area").asString());
                list.get(i).setBoss(record.get("boss").asString());
                list.get(i).setPlanedMoney(record.get("planedMoney").asString());
            }
        }

        session.close();
        driver.close();

        return list;
    }
}
