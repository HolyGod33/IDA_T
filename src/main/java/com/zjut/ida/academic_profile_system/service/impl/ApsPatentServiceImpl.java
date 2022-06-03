package com.zjut.ida.academic_profile_system.service.impl;

import com.zjut.ida.academic_profile_system.dao.ApsPatentDao;
import com.zjut.ida.academic_profile_system.service.ApsPatentService;
import com.zjut.ida.entity.Patent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author kokoryh on 2022/5/30
 */
@Service
public class ApsPatentServiceImpl implements ApsPatentService {

    @Autowired
    private ApsPatentDao patentDao;

    @Override
    @Transactional(readOnly = true)
    public List<Patent> findPatentsByScholarId(Integer scholarId, Integer limit, String year) {
        String base1 = "match (s1:Scholar)-[]-(p1:Patent) where id(s1)=" + scholarId;
        String y = "";
        String base2 = " and p1.applyDate is not null with p1 order by left(p1.applyDate,4) desc";
        String l = "";
        String base3 = " match p=(p1)-[]-(s2:Scholar) return p";
        if (!Objects.equals(year, "")) {
            y = " and left(p1.applyDate,4)='" + year + "'";
        }
        if (limit != 0) {
            l = " limit " + limit;
        }
        String cql = base1 + y + base2 + l + base3;

        List<Patent> patentList = patentDao.findPatentsByScholarId(cql);
        return patentList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> findPublishPatentCountByScholarId(Integer scholarId) {
        List<Map<String, Object>> publishPatentCount = patentDao.findPublishPatentCountByScholarId(scholarId);
//        publishArticleCount.removeIf(item -> item.get("year") == null);
        return publishPatentCount;
    }

}
