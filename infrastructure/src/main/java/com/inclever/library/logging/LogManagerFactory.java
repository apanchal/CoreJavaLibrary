package com.inclever.library.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.inclever.library.configuration.LibraryConfiguration;
public class LogManagerFactory {

    /**
     * The LogManagerFactory private instance
     */
    private static LogManagerFactory logManagerFactory = null;

    /**
     * Spring Contexts
     */
    private static final String[] CONTEXTS = { "inclever-config.xml" };

    /**
     * The Core Configuration instance
     */
    private LibraryConfiguration coreConfiguration = null;

    static {
        logManagerFactory = new LogManagerFactory();
        System.out.println("*** Blocking standard System.out. *** ");
        InterceptionManager.getInstance().intercept();
    }

    /**
     * Returns LogManagerfactory instance
     * 
     * @return LogManagerFactory The LogManagerfactory static instance
     */
    public static LogManagerFactory getInstance() {
        if (logManagerFactory == null) {
            logManagerFactory = new LogManagerFactory();
        }
        return logManagerFactory;
    }

    /**
     * This constructor will use the DynamicResourceBundle to get the type of
     * logger that we are using
     */
    @SuppressWarnings("resource")
    private LogManagerFactory() {

        // read configuration file from classpath.
        ApplicationContext context = null;
        try {
            System.out.println("Loading Core Configuration...");
            context = new ClassPathXmlApplicationContext(CONTEXTS, true);

            coreConfiguration = (LibraryConfiguration) context.getBean(LibraryConfiguration.BEAN_ID,
                    LibraryConfiguration.class);
        } catch (Exception exception) {
            throw new RuntimeException("Configuration File 'inclever-config.xml' is not found in classpath.",
                    exception);
        }

    }

    public Logger getLogger(Class<?> loggingClass) {
        return LoggerFactory.getLogger(loggingClass);
    }

    public LibraryConfiguration getCoreConfiguration() {
        return coreConfiguration;
    }

}
