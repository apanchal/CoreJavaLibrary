/*
 
 */

package com.inclever.library.exception.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.slf4j.Logger;

import com.inclever.library.logging.LogManagerFactory;

public class Loader {

    private static Logger log = LogManagerFactory.getInstance().getLogger(Loader.class);

    public static final InputStream getResourceAsStream(String file) {
        return getResourceAsStream(null, file);
    }

    public static final InputStream getResourceAsStream(Object caller, String file) {
        InputStream stream = null;

        if (caller != null) {
            log.trace("Trying to load '{}' using " + "this.getClass().getClassLoader().getResourceAsStream()", file);
            stream = caller.getClass().getClassLoader().getResourceAsStream(file);
        }

        if (stream == null) {
            log.trace("Load failed. Trying to load '{}' using " + "Thread.currentThread().getContextClassLoader()."
                    + "getResourceAsStream()", file);
            stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
        }

        if (stream == null) {
            log.trace("Load failed. Trying to load '{}' " + "using FileInputStream(String)", file);
            try {
                stream = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                log.error("File not found", e);
            }
        }

        if (stream == null) {
            log.trace("Load failed. Trying to load '{}' " + "using FileInputStream(File)", file);
            try {
                stream = new FileInputStream(new File(file));
            } catch (FileNotFoundException e) {
                log.error("File not found", e);
            }
        }

        return stream;
    }

}
