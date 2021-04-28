package com.fangaoxs.userserver.anno;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/12/17:27
 * @Description:    登入登出的注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthLog {
    String value() default "";
}
