package com.zjut.ida.recommend.tutor.module.home.controller;

import com.zjut.ida.recommend.tutor.core.annotation.Privilege;
import com.zjut.ida.recommend.tutor.module.home.service.TutorService;
import com.zjut.ida.recommend.tutor.module.home.vo.TutorVO;
import com.zjut.ida.recommend.tutor.utils.enums.PrivilegeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wly
 * @date 2021/5/16 20:20
 */
@Controller
@RequestMapping("tutor")
public class TutorController {
    @Autowired
    private TutorService tutorService;

    @Privilege(PrivilegeEnum.LoginPriv)
    @GetMapping("detail")
    public String detail(@RequestParam("tutorNeo4jId") Long tutorNeo4jId,
                         Model model) {
        TutorVO tutor = tutorService.getTutorByNeo4jId(tutorNeo4jId);
        model.addAttribute("relative", tutor);

        return "tutor/tutor_detail";
    }

    @GetMapping("studySpeciality/search")
    public String doMain(@RequestParam("collegeName") String collegeName,
                         Model model) {
        model.addAttribute("labelList", tutorService.getStudySpecialityList(collegeName));

        return "tutor/tutor_study_speciality_list";
    }
}
