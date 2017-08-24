package com.yance520.itpower.annotation;

import java.lang.annotation.*;

/**
 * Author : 杨杨
 * Date : 2017/08/20
 * Description :初始化继承BaseService的service
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BaseService {

}
