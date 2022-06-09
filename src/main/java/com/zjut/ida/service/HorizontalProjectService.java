package com.zjut.ida.service;

import com.zjut.ida.entity.HorizontalProject;
import com.zjut.ida.entity.VerticalProject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @author Casterx on 2019/10/27.
 */
public interface HorizontalProjectService {

    List<HorizontalProject> findHorizontalProjectsByScholarName(String scholarName);

    List<HorizontalProject> findHorizontalProjectsById(List<Long> horizontalProjectIdList);

    List<HorizontalProject> findColdStartByHistoryCount(int topN);

}
