package com.yance520.itpower.annotation;

import java.lang.annotation.*;

/**
 * Author : 杨杨
 * Date : 2017/08/20
 * Description :
 *
 * 忽略Token验证  校验sign
 * 如果有@OpenAuth注解，则不验证token,而是校验sigin
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OpenAuth {

}
