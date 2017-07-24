/*

 */

package com.inclever.library.exception.core;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

import com.inclever.library.exception.annotation.ExceptionHandler;
import com.inclever.library.exception.ignorer.IgnoringHelper;
import com.inclever.library.exception.util.Utils;
import com.inclever.library.logging.LogManagerFactory;

public class HandlerContextPopulator {

    private static final Logger LOGGER = LogManagerFactory.getInstance().getLogger(HandlerContextPopulator.class);

    // singleton
    private static HandlerContextPopulator instance = new HandlerContextPopulator();

    // class handlers' mapping: <classname,Mapper>
    private Map<String, HandlerMapper> map = Collections.synchronizedMap(new HashMap<String, HandlerMapper>());

    private HandlerContextPopulator() {

    }

    public static HandlerContextPopulator getInstance() {
        return instance;
    }

    public void populate(String className, HandlerContext context) {
        // the has already been analized?
        if (!context.isHandlerConfigured(className)) {
            // the class must be analyzed?
            if (!IgnoringHelper.ignoreClass(className)) {
                LOGGER.trace("-------> Inspecting class '{}'", className);
                Class<?> annotatedClass = Utils.forName(className);
                if (annotatedClass == null) {
                    throw new IllegalArgumentException("Could not process class '" + className + "'");
                }
                processClass(annotatedClass, context);
                context.setHandlerConfigured(className, true);
                LOGGER.trace("<------- end of class '{}' inspection", className);
            } else {
                LOGGER.trace("Ignoring inspection of class '{}'", className);
            }
        } else {
            LOGGER.trace("Ignoring inspection of class '{}'. " + "Class already analyzed", className);
        }
    }

    // create mapper for class
    private void processClass(Class<?> annotatedClass, HandlerContext context) {
        String className = annotatedClass.getName();

        // is there an ExceptionHandler defined for this class?
        if (annotatedClass.isAnnotationPresent(ExceptionHandler.class)) {
            ExceptionHandler globalHandler = annotatedClass.getAnnotation(ExceptionHandler.class);

            // create the mapper according parameters present
            // in class' annotation
            HandlerMapper globalMapper = createMapper(globalHandler);
            map.put(className, globalMapper);

        } else {
            LOGGER.trace("No exception mapper specified for class '{}'. " + "Using default", className);
        }

        // the processing methods
        processMethods(annotatedClass, context);
    }

    // create mappers for methods
    private void processMethods(Class<?> annotatedClass, HandlerContext context) {
        String className = annotatedClass.getName();
        Method[] methods = annotatedClass.getDeclaredMethods();

        for (Method method : methods) {
            String fullMethodName = className + "." + method.getName();

            // handlers' mapping
            HandlerMapper methodMapper;

            // is there an ExceptionHandler defined for this method?
            if (method.isAnnotationPresent(ExceptionHandler.class)) {
                ExceptionHandler methodHandler = method.getAnnotation(ExceptionHandler.class);

                // create the mapper according parameters present in
                // method's annotation
                methodMapper = createMapper(methodHandler);
            }
            // use class' mapper
            else {
                LOGGER.trace("No annotation vaid found in method {}, " + "using mapper from class", method);
                methodMapper = map.get(className);
            }

            if (methodMapper != null) {
                // puts mapper into context
                context.addMapper(fullMethodName, methodMapper);

                LOGGER.trace("Mapper associated: {} --> [{}]", fullMethodName, methodMapper);
            }
        }
    }

    // priority for annotation parameters --> handler, mapper
    private HandlerMapper createMapper(ExceptionHandler annotation) {
        HandlerMapper result;

        // get mapper from annotation
        HandlerMapper mapper = (HandlerMapper) Utils.createInstance(annotation.mapper());

        Handler handler = (Handler) Utils.createInstance(annotation.handler());

        // if default handler has been specified, we use
        // the mapper configuration
        if (handler instanceof DefaultHandler) {
            result = mapper;
        } else {
            // if a custom handler has been specified, it has
            // priority above mapper
            result = new DefaultMapper(handler);
        }

        return result;
    }
}
