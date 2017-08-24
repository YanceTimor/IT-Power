package com.yance520.itpower.annotation;


import java.lang.annotation.*;

/**
 * Author : 杨杨
 * Date : 2017/08/20
 * Description :
 * 直接通过注解的方式指定需要访问的数据源，
 * 比如在dao层使用@DataSource("xxx")就指定访问数据源xxx
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface DataSource {
	String value() default "";
}
