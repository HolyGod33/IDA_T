package com.zjut.ida.recommend.tutor.module.common.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zjut.ida.recommend.tutor.config.SysStudentHolder;
import com.zjut.ida.recommend.tutor.core.entity.SysStudentFavorite;
import com.zjut.ida.recommend.tutor.core.exception.BusinessException;
import com.zjut.ida.recommend.tutor.dao.SysStudentFavoriteMapper;
import com.zjut.ida.recommend.tutor.utils.enums.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wly
 * @date 2021/5/4 13:02
 */
@Service
public class CommonFavoriteService {
    @Autowired
    private SysStudentFavoriteMapper favoriteMapper;
    @Autowired
    private SysStudentHolder studentHolder;

    public Boolean isFavorite(String studentId, Long tutorNeo4jId) {
        return favoriteMapper.exist(studentId, tutorNeo4jId);
    }

    public void add(Long tutorNeo4jId) {
        String studentId = studentHolder.getStudent().getStudentId();
        if (isFavorite(studentId, tutorNeo4jId)) {
            return;
        }
        SysStudentFavorite favorite = new SysStudentFavorite();
        favorite.setStudentId(studentId);
        favorite.setTutorNeo4jId(tutorNeo4jId);
        if (favoriteMapper.insert(favorite) != 1) {
            throw new BusinessException(ResponseCode.FAVORITE_INSERT_ERROR);
        }
    }

    public void delete(Long tutorNeo4jId) {
        String studentId = studentHolder.getStudent().getStudentId();
        SysStudentFavorite favorite = favoriteMapper.selectOne(Wrappers.<SysStudentFavorite>lambdaQuery()
                .select(SysStudentFavorite::getFavoriteId)
                .eq(SysStudentFavorite::getStudentId, studentId)
                .eq(SysStudentFavorite::getTutorNeo4jId, tutorNeo4jId));
        if (favorite == null) {
            return;
        }
        if (favoriteMapper.deleteById(favorite.getFavoriteId()) != 1) {
            throw new BusinessException(ResponseCode.FAVORITE_DELETE_ERROR);
        }
    }
}
