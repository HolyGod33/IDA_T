package com.zjut.ida.academic_profile_system.service.impl;

import com.zjut.ida.academic_profile_system.entity.*;
import com.zjut.ida.academic_profile_system.service.DataService;
import com.zjut.ida.academic_profile_system.utils.FSearchTool;
import com.zjut.ida.academic_profile_system.utils.ObjectUtils;
import com.zjut.ida.academic_profile_system.utils.ReadUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kokoryh on 2022/6/1
 */
@Service
public class DataServiceImpl implements DataService {

    List<Interest> interestList = ReadUtils.readCSVFileToBean("aps/interest.csv", Interest.class);
    List<ScholarRelation> scholarRelations = ReadUtils.readCSVFileToBean("aps/relations.csv", ScholarRelation.class);
    List<CoAuthor> coAuthorList = ReadUtils.readCSVFileToBean("aps/Co_Author.csv", CoAuthor.class);
    List<Statistics> statisticsList = ReadUtils.readCSVFileToBean("aps/statistics.csv", Statistics.class);
    List<SimilarScholar> similarScholarList = ReadUtils.readCSVFileToBean("aps/articleSimilar.csv", SimilarScholar.class);

    public DataServiceImpl() throws IOException {
    }

    @Override
    public List<List<Object>> findInterestByScholarId(Integer scholarId) {
        String id = scholarId.toString();
        try {
            FSearchTool tool = new FSearchTool(interestList, "id");
            List<Object> searchList = tool.searchTasks(id);
            List<List<Object>> resultList = new ArrayList<>();

            for (Object item : searchList) {
                List<Object> interest = new ArrayList<>();
                interest.add(ObjectUtils.getFieldValueByName("year", item));
                interest.add(ObjectUtils.getFieldValueByName("count", item));
                interest.add(ObjectUtils.getFieldValueByName("keyword", item));
                resultList.add(interest);
            }
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Object> findRelationByScholarId(Integer scholarId) {
        String id = scholarId.toString();
        try {
            FSearchTool tool = new FSearchTool(scholarRelations, "id");
            return tool.searchTasks(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Object> findCoAuthorByScholarId(Integer scholarId) {
        String id = scholarId.toString();
        try {
            FSearchTool tool = new FSearchTool(coAuthorList, "sourceID");
            List<Object> coAuthorList = tool.searchTasks(id);
            int size = coAuthorList.size();

            for (int i = 0; i < size; i++) {
                List<Object> others = tool.searchTasks(ObjectUtils.getFieldValueByName("targetID", coAuthorList.get(i)).toString());
                for (int j = i + 1; j < size; j++) {
                    for (Object item : others) {
                        String str1 = (String) ObjectUtils.getFieldValueByName("target", item);
                        String str2 = (String) ObjectUtils.getFieldValueByName("target", coAuthorList.get(j));
                        if (str1.equals(str2)) {
                            coAuthorList.add(item);
                        }
                    }
                }
            }
            return coAuthorList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Map<String, Object> findStatisticsByScholarId(Integer scholarId) {
        String id = scholarId.toString();
        try {
            FSearchTool tool = new FSearchTool(statisticsList, "id");
            List<Object> list = tool.searchTasks(id);
            Statistics statistics = (Statistics) list.get(0);

            // 10个关联学者为1分,上限为5分
            Integer baseSociability = 10;
            // 50个关键词为1分,上限为5分
            Integer baseDiversity = 50;
            // 两年内每发表两篇论文算1分,上限为5分
            Integer baseActivity = 2;

            Integer sociability = statistics.getPartnerCount() >= 50 ? 5 : statistics.getPartnerCount() / baseSociability;
            Integer diversity = statistics.getKeywordCount() >= 250 ? 5 : statistics.getKeywordCount() / baseDiversity;
            Integer activity = statistics.getNearlyTwoYearArticleCount() >= 10 ? 5 : statistics.getNearlyTwoYearArticleCount() / baseActivity;

            int hIndex = 0;
            int gIndex = 0;
            String tmp = StringUtils.substring(statistics.getCitationSort(), 1, -1);
            String[] citeList = tmp.split(",");
            // 计算h-index
            for (int i = 0; i < citeList.length; i++) {
                if (i + 1 > Integer.parseInt(citeList[i])) {
                    hIndex = i;
                    break;
                }
            }
            // 计算g-index
            int p = 0;
            for (int i = 0; i < citeList.length; i++) {
                p += Integer.parseInt(citeList[i]);
                if (p < (i + 1) * (i + 1)) {
                    gIndex = i;
                    break;
                }
            }
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("papers", statistics.getPapers());
            resultMap.put("citation", statistics.getCitation());
            resultMap.put("hIndex", hIndex);
            resultMap.put("gIndex", gIndex);
            resultMap.put("sociability", sociability);
            resultMap.put("diversity", diversity);
            resultMap.put("activity", activity);
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Object> findSimilarScholarByScholarId(Integer scholarId) {
        String id = scholarId.toString();
        try {
            FSearchTool tool = new FSearchTool(similarScholarList, "ID1");
            return tool.searchTasks(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
