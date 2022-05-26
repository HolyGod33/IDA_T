package com.zjut.ida.recommend.tutor.module.dashboard.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.zjut.ida.recommend.tutor.config.SysStudentHolder;
import com.zjut.ida.recommend.tutor.core.entity.SysCF;
import com.zjut.ida.recommend.tutor.core.entity.SysStudent;
import com.zjut.ida.recommend.tutor.core.entity.SysStudentFavorite;
import com.zjut.ida.recommend.tutor.core.exception.BusinessException;
import com.zjut.ida.recommend.tutor.core.m2nentity.NSysStudent;
import com.zjut.ida.recommend.tutor.core.neo4j.Neo4jTutor;
import com.zjut.ida.recommend.tutor.core.page.SimplePageInfo;
import com.zjut.ida.recommend.tutor.dao.Neo4jTutorRepository;
import com.zjut.ida.recommend.tutor.dao.SysCFMapper;
import com.zjut.ida.recommend.tutor.dao.SysStudentFavoriteMapper;
import com.zjut.ida.recommend.tutor.dao.SysStudentMapper;
import com.zjut.ida.recommend.tutor.m2ndao.SysStudentDao;
import com.zjut.ida.recommend.tutor.module.dashboard.vo.StudentVO;
import com.zjut.ida.recommend.tutor.module.dashboard.vo.TutorVO;
import com.zjut.ida.recommend.tutor.module.home.dto.StudentDTO;
import com.zjut.ida.recommend.tutor.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.zjut.ida.recommend.tutor.utils.enums.ResponseCode.USER_UPDATE_ERROR;

/**
 * @author wly
 * @date 2021/4/26 14:05
 */
@Service("DashboardStudentService")
public class StudentService {
    @Autowired
    private SysCFMapper cfMapper;
    @Autowired
    private SysStudentFavoriteMapper favoriteMapper;
    @Autowired
    private Neo4jTutorRepository tutorRepository;
    @Autowired
    private SysStudentMapper studentMapper;
    @Autowired
    private SysStudentHolder studentHolder;

    @Autowired
    private SysStudentDao studentDao;

    /**
     * 获取学生信息
     *
     * @return 学生信息
     */
    public StudentVO getInfo() {
        NSysStudent student = studentHolder.getStudent();
        StudentVO studentVO = new StudentVO();
        BeanUtils.copyProperties(student, studentVO);
        if (StringUtils.isNotBlank(studentVO.getStudySpeciality())) {
            studentVO.setStudySpeciality(Arrays.stream(studentVO.getStudySpeciality().split("&"))
                    .reduce((x, y) -> x + "，" + y).orElse(""));
        }
        return studentVO;
    }


    /**
     * 修改用户信息
     *
     * @param dto 用户信息
     * @return 修改成功
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean changeInfo(StudentDTO dto) {
        dto.checkField();
        NSysStudent student = new NSysStudent();
        BeanUtils.copyProperties(dto, student);
        NSysStudent neo4jStudent=studentDao.findNSysStudentByStudentId(dto.getStudentId());

        // 更新密码
        if (StringUtils.isNotBlank(dto.getPassword())) {
            student.setHashSalt(CommonUtils.random(36, 4));
            student.setPassword(CommonUtils.MD5(student.getPassword() + student.getHashSalt()));
        }
        // 更新兴趣爱好
        if (!CollectionUtils.isEmpty(dto.getStudySpecialityList())) {
            List<String> tempList = dto.getStudySpecialityList().stream()
                    .map(x -> x = StringUtils.strip(x, "\n "))
                    .collect(Collectors.toList());
            student.setStudySpeciality(StringUtils.join(tempList, "&"));
        }
        // 更新
        student.setStudentId(studentHolder.getStudentId());
        student.setId(studentHolder.getStudent().getId());
        student.setTutorList(neo4jStudent.getTutorList());
//        if (studentMapper.updateById(student) != 1) {
//            throw new BusinessException(USER_UPDATE_ERROR);
//        }
        System.out.println("StudentService.java====changeInfo===="+student);
        studentDao.save(student);
        return true;
    }

    /**
     * 修改用户信息StudySpeciality
     *
     * @param dto 用户信息StudySpeciality
     * @return 修改成功
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean changeStudySpecialityInfo(StudentDTO dto) {
        dto.checkField();
        NSysStudent student = new NSysStudent();
        NSysStudent neo4jStudent=studentDao.findNSysStudentByStudentId(dto.getStudentId());
        BeanUtils.copyProperties(neo4jStudent, student);

        System.out.println("dto.getStudySpecialityList()="+dto.getStudySpecialityList());

        // 更新兴趣爱好
        if (!CollectionUtils.isEmpty(dto.getStudySpecialityList())) {
            List<String> tempList = dto.getStudySpecialityList().stream()
                    .map(x -> x = StringUtils.strip(x, "\n "))
                    .collect(Collectors.toList());
            student.setStudySpeciality(StringUtils.join(tempList, "&"));
        }else{
            student.setStudySpeciality(null);
        }
        // 更新
        student.setStudentId(studentHolder.getStudentId());
        student.setId(studentHolder.getStudent().getId());
//        if (studentMapper.updateById(student) != 1) {
//            throw new BusinessException(USER_UPDATE_ERROR);
//        }
        System.out.println("StudentService.java====changeInfo===="+student);
        studentDao.save(student);
        return true;
    }


    public SimplePageInfo<TutorVO> getHistoryList(int pageNum, int pageSize) {
        String studentId = studentHolder.getStudent().getStudentId();
//        String studentId = "4794621712";
        PageHelper.startPage(pageNum, pageSize);

        System.out.println("student="+studentDao.findNSysStudentByStudentId(studentId));
        List<Neo4jTutor> tutorList=studentDao.findNSysStudentByStudentId(studentId).getHistoryTutorList();
        System.out.println("tutorList="+tutorList);
        tutorList=tutorList.size()==0?null:tutorList;
//        SimplePageInfo<SysCF> pageInfo = new SimplePageInfo<>(cfMapper.selectList(Wrappers.<SysCF>lambdaQuery()
//                .select(SysCF::getTutorNeo4jId, SysCF::getCreateTime)
//                .eq(SysCF::getStudentId, studentId)
//                .orderByDesc(SysCF::getCreateTime)));
        SimplePageInfo<Neo4jTutor> pageInfo = new SimplePageInfo<>(tutorList);
        System.out.println("pageInfo="+pageInfo);
        List<TutorVO> tutorVOList=null;
        if(tutorList!=null){
            tutorVOList = tutorList.stream().map(tutor -> {
    //            Neo4jTutor tutor = tutorRepository.findOneByNeo4jId(cf.getTutorNeo4jId());
                TutorVO tutorVO = new TutorVO();
                BeanUtils.copyProperties(tutor, tutorVO);
    //            tutorVO.setCreateTime(cf.getCreateTime());
                return tutorVO;
            }).collect(Collectors.toList());
        }
        System.out.println("tutorVOList="+tutorVOList);
        return new SimplePageInfo<>(pageInfo, tutorVOList);
    }



    public SimplePageInfo<TutorVO> getFavoriteList(int pageNum, int pageSize) {
        String studentId = studentHolder.getStudent().getStudentId();
        PageHelper.startPage(pageNum, pageSize);
        SimplePageInfo<SysStudentFavorite> pageInfo = new SimplePageInfo<>(favoriteMapper.selectList(Wrappers.<SysStudentFavorite>lambdaQuery()
                .select(SysStudentFavorite::getTutorNeo4jId, SysStudentFavorite::getCreateTime)
                .eq(SysStudentFavorite::getStudentId, studentId)
                .orderByDesc(SysStudentFavorite::getCreateTime)));
        List<TutorVO> tutorVOList = pageInfo.getList().stream().map(favorite -> {
            Neo4jTutor tutor = tutorRepository.findOneByNeo4jId(favorite.getTutorNeo4jId());
            TutorVO tutorVO = new TutorVO();
            BeanUtils.copyProperties(tutor, tutorVO);
            tutorVO.setCreateTime(favorite.getCreateTime());
            return tutorVO;
        }).collect(Collectors.toList());
        return new SimplePageInfo<>(pageInfo, tutorVOList);
    }


    public boolean addHistory(Long tutorNeo4jId) {
        studentDao.saveHistory(studentHolder.getStudentId(),tutorNeo4jId);
        return true;
    }
}
