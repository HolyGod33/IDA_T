package com.zjut.ida.recommend.tutor.core.annotation;

import com.zjut.ida.recommend.tutor.utils.enums.PrivilegeEnum;

import java.lang.annotation.*;

/**
 * @author wly
 * @date 2021/4/22 10:42
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface Privilege {
    PrivilegeEnum value() default PrivilegeEnum.AllPriv;
}
