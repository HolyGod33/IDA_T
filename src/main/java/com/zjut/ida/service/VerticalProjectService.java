package com.zjut.ida.service;

import com.zjut.ida.entity.Patent;
import com.zjut.ida.entity.VerticalProject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Casterx on 2019/10/23.
 */
public interface VerticalProjectService {


    List<VerticalProject> findVerticalProjectByScholarName(String scholarName);

    List<VerticalProject> findVerticalProjectsById(List<Long> verticalProjectIdList);
}
