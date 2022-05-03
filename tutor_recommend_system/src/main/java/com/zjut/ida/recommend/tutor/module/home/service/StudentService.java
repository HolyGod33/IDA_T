package com.zjut.ida.recommend.tutor.module.home.service;

import com.alibaba.fastjson.JSONObject;
import com.zjut.ida.recommend.tutor.core.entity.SysStudent;
import com.zjut.ida.recommend.tutor.core.exception.BusinessException;
import com.zjut.ida.recommend.tutor.core.m2nentity.NSysStudent;
import com.zjut.ida.recommend.tutor.dao.ModelStudentMapMapper;
import com.zjut.ida.recommend.tutor.dao.SysStudentMapper;
import com.zjut.ida.recommend.tutor.module.home.dto.RegisterDTO;
import com.zjut.ida.recommend.tutor.module.home.vo.StudentVO;
import com.zjut.ida.recommend.tutor.m2ndao.SysStudentDao;
import com.zjut.ida.recommend.tutor.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.zjut.ida.recommend.tutor.utils.enums.ResponseCode.*;

/**
 * @author wly
 * @date 2021/4/26 14:05
 */
@Service("HomeStudentService")
public class StudentService {
    @Autowired
    private SysStudentMapper studentMapper;
    @Autowired
    private ModelStudentMapMapper studentMapMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SysStudentDao sysStudentDao;
    /**
     * 登录
     *
     * @param studentId 学生学号
     * @param password  密码
     * @return token
     */
    public JSONObject login(String studentId, String password) {
        NSysStudent student = sysStudentDao.findNSysStudentByStudentId(studentId);
        // 用户不存在
        if (student == null) {
            throw new BusinessException(USER_NOT_EXIST);
        }
        // 密码不正确
        String md5Password = CommonUtils.MD5(password + student.getHashSalt());
        if (md5Password == null || !md5Password.equalsIgnoreCase(student.getPassword())) {
            throw new BusinessException(USER_WRONG_PASSWORD);
        }

        StudentVO studentVO = new StudentVO();
        BeanUtils.copyProperties(student, studentVO);

        String token = CommonUtils.uuid();
        redisTemplate.opsForValue().set("token" + token, student);
        redisTemplate.expire("token" + token, 2, TimeUnit.HOURS);

        JSONObject result = new JSONObject();
        result.put("user", studentVO);
        result.put("token", token);

        return result;
    }

    /**
     * 注册(Mysql)
     *
     * @param dto 注册信息
     * @return 注册成功/失败
     */
//    @Transactional(rollbackFor = Exception.class)
//    public boolean register(RegisterDTO dto) {
//        SysStudent student = studentMapper.selectOne(Wrappers.<SysStudent>lambdaQuery()
//                .eq(SysStudent::getStudentId, dto.getStudentId()));
//        // 用户已存在
//        if (student != null) {
//            throw new BusinessException(USER_EXIST);
//        }
//        student = new SysStudent();
//        BeanUtils.copyProperties(dto, student);
//        // 设置密码
//        student.setHashSalt(CommonUtils.random(36, 4));
//        student.setPassword(CommonUtils.MD5(student.getPassword() + student.getHashSalt()));
//        // 设置兴趣爱好
//        if (!CollectionUtils.isEmpty(dto.getStudySpecialityList())) {
//            List<String> tempList = dto.getStudySpecialityList().stream()
//                    .map(x -> x = StringUtils.strip(x, "\n "))
//                    .collect(Collectors.toList());
//            student.setStudySpeciality(StringUtils.join(tempList, "&"));
//        }
//        // 插入
//        if (studentMapper.insert(student) != 1) {
//            throw new BusinessException(USER_REGISTER_ERROR);
//        }
//        return true;
//    }


    /**
     * 注册(Neo4j)
     *
     * @param dto 注册信息
     * @return 注册成功/失败
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean register(RegisterDTO dto) {
        NSysStudent student = sysStudentDao.findNSysStudentByStudentId(dto.getStudentId());
        // 用户已存在
        if (student != null) {
            throw new BusinessException(USER_EXIST);
        }
        System.out.println(student);


        student = new NSysStudent();
        BeanUtils.copyProperties(dto, student);
        // 设置密码
        student.setHashSalt(CommonUtils.random(36, 4));
        student.setPassword(CommonUtils.MD5(student.getPassword() + student.getHashSalt()));
        // 设置兴趣爱好
        if (!CollectionUtils.isEmpty(dto.getStudySpecialityList())) {
            List<String> tempList = dto.getStudySpecialityList().stream()
                    .map(x -> x = StringUtils.strip(x, "\n "))
                    .collect(Collectors.toList());
            student.setStudySpeciality(StringUtils.join(tempList, "&"));
        }
        sysStudentDao.save(student);
        return true;
    }

    /**
     * 按学生学号找到重映射id
     *
     * @param studentId 学生学号
     * @return 重映射id
     */
    public Long getRemapIdByStudentId(String studentId) {
        return studentMapMapper.findRemapIdByStudentId(studentId);
    }
}
