package com.zjut.ida.service;

import com.zjut.ida.entity.HorizontalProject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Casterx on 2019/10/27.
 */
public interface HorizontalProjectService {

    List<HorizontalProject> findHorizontalProjectsByScholarName(String scholarName);

}
