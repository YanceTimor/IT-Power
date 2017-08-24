package com.yance520.itpower.annotation;

import java.lang.annotation.*;

/**
 * Author : 杨杨
 * Date : 2017/08/20
 * Description :
 *  忽略Token验证
 * 如果有@IgnoreAuth注解，则不验证token
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreAuth {

}
