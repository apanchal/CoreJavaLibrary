/*

 */

package com.inclever.library.exception.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;

import com.inclever.library.exception.ignorer.IgnoringHelper;
import com.inclever.library.exception.util.Utils;
import com.inclever.library.logging.LogManagerFactory;

public class HandlerHelper {

    private static final Logger LOGGER = LogManagerFactory.getInstance().getLogger(HandlerHelper.class);

    private HandlerContext context = HandlerContext.getInstance();

    private HandlerContextPopulator populator = HandlerContextPopulator.getInstance();

    private Handler handler;

    public Handler getHandler() {
        return handler;
    }

    // tackle erros acording handlers' configuration
    public Throwable handle(Throwable throwable, Object... params) {

        StackTraceElement[] trace = throwable.getStackTrace();

        // configure handlers for classes in StackTrace
        for (StackTraceElement stackTraceElement : trace) {
            String className = stackTraceElement.getClassName();
            // analyze this class?
            if (!IgnoringHelper.ignoreClass(className)) {
                // configure handlers
                populator.populate(className, context);
            }
        }

        // get the correct Handler
        handler = context.getHandler(throwable, trace);

        // try to find a method for handle the specific exception
        Method method = null;
        try {
            method = getHandleMethod(handler.getClass(), throwable.getClass());
        } catch (Exception e) {
            // specific method not found
        }

        Throwable result;
        if (method != null) {
            LOGGER.debug("Appropriate handling method found: '{}'", method);
            try {
                LOGGER.debug("Doing handling. Executing method: '{}'", method);
                result = (Throwable) method.invoke(handler, throwable, params);
                LOGGER.debug("Execution successful!");
            } catch (Exception e) {
                result = throwable;
                LOGGER.error("Could not execute method: '" + method + "'", e);
            }
        }
        // using generic method. See Handler.handle()
        else {
            if (params != null && params.length > 0) {
                result = handler.handle(throwable, params);
            } else {
                result = handler.handle(throwable);
            }
        }

        return result;
    }

    // select the correct method to invoke
    private Method getHandleMethod(Class<?> clazz, Class<? extends Throwable> throwableClass) {
        List<Method> handleMethods = new ArrayList<>();
        try {
            // filter methods with signature
            // "Throwable handle(throwableClass,Object...)"
            Class<?>[] paramTypes = new Class<?>[] { throwableClass, Object[].class };
            Method[] methods = getAllDeclaredMethods(clazz);
            for (Method method : methods) {
                // if method name is 'handle' and its return type is subclass of
                // Throwable
                if ("handle".equals(method.getName())
                        && Utils.isAssignableFrom(Throwable.class, method.getReturnType())) {
                    Class<?>[] methodTypes = method.getParameterTypes();
                    // compatible method found
                    if (Utils.compatibleParams(paramTypes, methodTypes)) {
                        handleMethods.add(method);
                    }
                }
            }

            if (LOGGER.isTraceEnabled()) {
                LOGGER.trace("Methods filtered:");
                for (Method method : methods) {
                    LOGGER.trace(method.toString());
                }
            }
            // sort by hierarchy
            Collections.sort(handleMethods, comparator);

        } catch (Exception e) {
            LOGGER.error("Error reading method", e);
        }
        return handleMethods.size() > 0 ? handleMethods.get(0) : null;
    }

    private Comparator<Method> comparator = new Comparator<Method>() {
        public int compare(Method m1, Method m2) {
            int result = -1;
            if (m1.equals(m2)) {
                result = 0;
            } else {
                Class<?> c1 = m1.getParameterTypes()[0];
                Class<?> c2 = m2.getParameterTypes()[0];
                if (c1.isAssignableFrom(c2)) {
                    result = 1;
                } else {
                    result = -1;
                }
            }
            return result;
        }
    };

    // returns all declared methods (from class and all superclass)
    private final Method[] getAllDeclaredMethods(Class<?> cls) {
        List<Method> accum = new ArrayList<>();
        while (cls != null) {
            Method[] f = cls.getDeclaredMethods();
            for (int i = 0; i < f.length; i++) {
                accum.add(f[i]);
            }
            cls = cls.getSuperclass();
        }

        return (Method[]) accum.toArray(new Method[accum.size()]);
    }
}
