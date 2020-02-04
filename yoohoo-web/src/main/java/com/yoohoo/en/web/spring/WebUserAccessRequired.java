package com.yoohoo.en.web.spring;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 需要Web用户登陆的注解
 * 
 * @author  何云良
 * @version  [版本号, 2017年1月18日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WebUserAccessRequired
{
    
}
