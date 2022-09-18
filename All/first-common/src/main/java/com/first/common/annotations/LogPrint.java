package com.first.common.annotations;


import java.lang.annotation.*;

@Target(value = {ElementType.TYPE,ElementType.METHOD,ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface LogPrint {
    /**加在方法上的解释说明*/
    String value() default "";
    /**方法名*/
    String name() default "";
    /**日志记录到mongdb是否开启 ,默认false*/
    boolean isEnabled() default false;
}
