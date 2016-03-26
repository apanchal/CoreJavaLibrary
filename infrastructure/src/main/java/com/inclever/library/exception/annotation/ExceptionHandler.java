package com.inclever.library.exception.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.inclever.library.exception.core.DefaultHandler;
import com.inclever.library.exception.core.DefaultMapper;
import com.inclever.library.exception.core.Handler;
import com.inclever.library.exception.core.HandlerMapper;

/**
 * Handler annotation that defines which handles exceptions that occur in a
 * method
 * 
 * @author Ashish Panchal(aashish.panchal@gmail.com)
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface ExceptionHandler {

    // mapping strategy between handlers and methods
    Class<? extends HandlerMapper> mapper() default DefaultMapper.class;

    // Allow to declare the handler directly, instead of the mapper
    Class<? extends Handler> handler() default DefaultHandler.class;
}
