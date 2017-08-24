package com.yance520.itpower.annotation;

import java.lang.annotation.*;

/**
 * Author : 杨杨
 * Date : 2017/08/20
 * Description : 系统日志注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String value() default "";
}
