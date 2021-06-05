package com.zjut.ida.recommend.tutor.module.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.zjut.ida.recommend.tutor.core.annotation.Privilege;
import com.zjut.ida.recommend.tutor.module.common.service.CommonFavoriteService;
import com.zjut.ida.recommend.tutor.utils.Response;
import com.zjut.ida.recommend.tutor.utils.enums.PrivilegeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wly
 * @date 2021/5/4 13:50
 */
@RestController
@RequestMapping("favorite")
@Privilege(PrivilegeEnum.LoginPriv)
public class CommonFavoriteController {
    @Autowired
    private CommonFavoriteService favoriteService;

    @PostMapping("add")
    public JSONObject add(@RequestBody Long tutorNeo4jId) {
        favoriteService.add(tutorNeo4jId);
        return Response.bool(true);
    }

    @DeleteMapping("delete")
    public JSONObject delete(@RequestBody Long tutorNeo4jId) {
        favoriteService.delete(tutorNeo4jId);
        return Response.bool(true);
    }
}
