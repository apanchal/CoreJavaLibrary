/*
 * 
 */

package com.inclever.library.exception.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;

import com.inclever.library.logging.LogManagerFactory;

public class Utils {

    private static final Logger LOGGER = LogManagerFactory.getInstance().getLogger(Utils.class);

    private static final String DEFAULT_EXCEPTION_FILE = "exception.properties";

    private static final String EXCEPTION_EXT_FILE = "exception-ext.properties";

    public static OrderedProperties GLOBAL_PROPERTIES = new OrderedProperties();

    private static String[] EXCEPTION_FILES = { DEFAULT_EXCEPTION_FILE, EXCEPTION_EXT_FILE };

    static {
        GLOBAL_PROPERTIES = load(EXCEPTION_FILES);
    }

    public static final OrderedProperties load(String[] propertiesFiles) {
        OrderedProperties properties = new OrderedProperties();

        for (String propertiesFile : propertiesFiles) {
            LOGGER.debug("Trying to load '{}'", propertiesFile);

            // Add props from the resource 'propertiesFiles'
            InputStream in = Loader.getResourceAsStream(propertiesFile);
            if (in != null) {
                try {
                    properties.load(in);
                    in.close();
                    LOGGER.debug("'{}' loaded ({} entries found)", propertiesFile, properties.size());
                } catch (IOException e) {
                    LOGGER.debug("Error loading '{}'", propertiesFile);
                }
            } else {
                LOGGER.debug("'{}' not found", propertiesFile);
            }
        }

        return properties;
    }

    public static final Object createInstance(String className) {
        Object instance = null;
        try {
            instance = forName(className).newInstance();
        } catch (Exception e) {
            LOGGER.error("Error instantiating class: " + e.getMessage());
        }
        return instance;
    }

    public static final Object createInstance(Class<?> clazz, Object... args) {
        Object instance = null;
        try {
            Class<?>[] paramTypes = toClassArray(args);
            Constructor<?> toInvoke = clazz.getConstructor();
            Constructor<?>[] constructors = clazz.getConstructors();
            for (Constructor<?> constructor : constructors) {
                Class<?>[] constructorTypes = constructor.getParameterTypes();
                // get the first compatible constructor
                if (compatibleParams(paramTypes, constructorTypes)) {
                    toInvoke = constructor;
                    break;
                }
            }
            LOGGER.trace("Invoking constructor {}", toInvoke);
            instance = toInvoke.newInstance(args);

        } catch (Exception e) {
            LOGGER.error("Error creating instance", e);
        }
        return instance;
    }

    public static final String toString(Throwable throwable) {
        StringBuffer msg = new StringBuffer();
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            throwable.printStackTrace(pw);
            msg.append(sw.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg.toString();
    }

    public static final boolean compatibleParams(Class<?>[] paramTypes, Class<?>[] methodTypes) {
        boolean compatible = true;
        // same size?
        if (methodTypes.length == paramTypes.length) {
            // verify parameters
            for (int i = 0; i < methodTypes.length; i++) {
                // methodTypes[i] must be superclass of paramTypes[i]...
                if (!methodTypes[i].isAssignableFrom(paramTypes[i])) {
                    compatible = false;
                    break;
                }
            }
        } else {
            compatible = false;
        }
        return compatible;
    }

    public static final Class<?>[] toClassArray(Object... objects) {
        Class<?>[] classes = null;

        if (objects != null) {
            int i = 0;
            classes = new Class<?>[objects.length];
            for (Object object : objects) {
                classes[i++] = object.getClass();
            }
        }

        return classes;
    }

    private static Comparator<Class<?>> comparator = new Comparator<Class<?>>() {
        public int compare(Class<?> o1, Class<?> o2) {
            int result = 0;
            if (o1.equals(o2)) {
                result = 0;
            } else if (o1.isAssignableFrom(o2)) {
                result = 1;
            } else {
                result = -1;
            }
            return result;
        }
    };

    public static void sortByHierarchy(List<Class<?>> classes) {
        Collections.sort(classes, comparator);
    }

    public static void sortByHierarchy(Class<?>[] classes) {
        Arrays.sort(classes, comparator);
    }

    // necessary because of classloader issues
    public static final boolean isAssignableFrom(Class<?> class1, Class<?> class2) {

        boolean assignable = false;

        if (class1 == null || class2 == null) {
            assignable = false;
        } else if (class1.isAssignableFrom(class2)) {
            assignable = true;
        } else {
            assignable = isAssignableFrom(class1.getName(), class2.getName());
        }

        return assignable;
    }

    // necessary because of classloader issues
    public static final boolean isAssignableFrom(String className1, String className2) {

        boolean assignable = false;

        if (className1 == null || className2 == null) {
            assignable = false;
        } else if (className1.equals(className2)) {
            assignable = true;
        } else if (className1.contains(className2)) {
            assignable = true;
        } else if (className2.contains(className1)) {
            assignable = true;
        } else {
            try {
                ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
                Class<?> class_1 = Class.forName(className1, false, contextClassLoader);
                Class<?> class_2 = Class.forName(className2, false, contextClassLoader);
                assignable = class_1.isAssignableFrom(class_2);
            } catch (ClassNotFoundException e) {
                assignable = false;
            }
        }

        return assignable;
    }

    public static final Class<?> forName(String className) {
        Class<?> instance = null;
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            instance = contextClassLoader.loadClass(className);
        } catch (ClassNotFoundException e) {
            LOGGER.error("Could not create instance of '{}'", className, e);
        }

        return instance;
    }

    /**
     * Answers the unqualified class name for the provided class.
     */
    @SuppressWarnings("rawtypes")
    public static String getShortClassName(Class javaClass) {
        return getShortClassName(javaClass.getName());
    }

    /**
     * Answers the unqualified class name from the specified String.
     */
    public static String getShortClassName(String javaClassName) {
        return javaClassName.substring(javaClassName.lastIndexOf('.') + 1);
    }

    /**
     * Answers the unqualified class name for the specified object.
     */
    public static String getShortClassName(Object object) {
        return getShortClassName(object.getClass());
    }

}
