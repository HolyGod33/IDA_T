package com.zjut.ida.recommend.tutor;

import com.zjut.ida.recommend.tutor.core.exception.BusinessException;
import com.zjut.ida.recommend.tutor.core.m2nentity.NSysScholar;
import com.zjut.ida.recommend.tutor.core.m2nentity.NSysStudent;
import com.zjut.ida.recommend.tutor.core.neo4j.Neo4jTutor;
import com.zjut.ida.recommend.tutor.core.page.SimplePageInfo;
import com.zjut.ida.recommend.tutor.dao.SysCFMapper;
import com.zjut.ida.recommend.tutor.m2ndao.SysScholarDao;
import com.zjut.ida.recommend.tutor.module.common.service.DictService;
import com.zjut.ida.recommend.tutor.module.dashboard.service.StudentService;
import com.zjut.ida.recommend.tutor.module.home.dto.RegisterDTO;
import com.zjut.ida.recommend.tutor.m2ndao.SysStudentDao;
import com.zjut.ida.recommend.tutor.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.zjut.ida.recommend.tutor.utils.enums.ResponseCode.USER_EXIST;

@SpringBootTest
class TutorRecommendSystemApplicationTests {


    @Autowired
    private SysStudentDao studentDao;


    @Autowired
    private DictService dictService;

    @Autowired
    private SysScholarDao sysScholarDao;

    @Autowired
    private SysCFMapper cfMapper;

    @Autowired
    private StudentService studentService;

    @Test
    void contextLoads() {
//        System.out.println(sysStudentDao.findNSysStudentByStudentId("201706022605"));
//        System.out.println(sysStudentDao.findNSysStudentByStudentId("479462712"));
//        System.out.println(sysStudentDao.findNSysStudentById(696893L));
//        System.out.println(sysStudentDao.findNSysStudentById(124345L));

        RegisterDTO dto = new RegisterDTO();
        dto.setStudentId("479462712");
        dto.setPassword("479462712");
        dto.setStudentName("徐洲帅");
        dto.setCollegeName("计算机科学与技术学院");
        dto.setStudentClass("专硕1903");
        dto.setStudentGender(0);
        dto.setAdmissionYear(2019);
        dto.setStudySpecialityList(Arrays.asList(new String[]{"计算机应用","科技哲学"}));
        System.out.println(dto);
        NSysStudent student = studentDao.findNSysStudentByStudentId(dto.getStudentId());
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
        studentDao.save(student);


        // 插入
//        if (studentMapper.insert(student) != 1) {
//            throw new BusinessException(USER_REGISTER_ERROR);
//        }
//        System.out.println(student);
//        return true;

    }

    @Test
    void testDictService(){
        System.out.println(dictService.getClassDict(2017, "计算机科学与技术学院"));
    }

    @Test
    void testSysScholarDao(){

//        System.out.println(sysScholarDao.findTutorRelativeIdListByTutorRemapId(1L));

//        List<Integer> remapList=Arrays.asList(new Integer[]{1,2,3,4});
//        System.out.println(sysScholarDao.findNeo4jIdsByRemapIds(remapList));

//        System.out.println(sysScholarDao.findNeo4jIdByRemapId(1L));

//        System.out.println(sysScholarDao.findRemapIdByNeo4jId(447058L));

        NSysStudent student = studentDao.findNSysStudentByStudentId("4794621712");
        System.out.println(student);
    }


    @Test
    void testSysStudentDao(){
//        System.out.println(studentDao.findTutorsByStudentId("201706022605"));
        studentDao.saveHistory("4794621712",439337L);
    }


    @Test
    void testStudentService(){
//        SimplePageInfo<SysCF> pageInfo = new SimplePageInfo<>(cfMapper.selectList(Wrappers.<SysCF>lambdaQuery()
//                .select(SysCF::getTutorNeo4jId, SysCF::getCreateTime)
//                .eq(SysCF::getStudentId, "201706061000")
//                .orderByDesc(SysCF::getCreateTime)));
//        System.out.println(pageInfo);


        NSysStudent student=studentDao.findNSysStudentByStudentId("4794621712");
        System.out.println(student);
        System.out.println();
        List<Neo4jTutor> tutorList=studentDao.findNSysStudentByStudentId("4794621712").getTutorList();
        SimplePageInfo<Neo4jTutor> pageInfo1 = new SimplePageInfo<>(tutorList);
        System.out.println(pageInfo1);
        System.out.println();


        System.out.println(studentService.getHistoryList(1, 5));
    }

    @Test
    void createHashSalt(){
        for(int i=0;i<1118;i++){
            String hash=CommonUtils.random(36, 4);
            String password=CommonUtils.MD5("123456" +hash );
            System.out.println(hash+" "+password);
        }

    }




}
