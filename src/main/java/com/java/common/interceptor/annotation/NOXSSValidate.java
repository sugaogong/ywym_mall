package com.java.common.interceptor.annotation;

import java.lang.annotation.*;

@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NOXSSValidate {
	boolean validate() default true;
}
