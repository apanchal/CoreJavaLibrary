/*
 
 */

package com.inclever.library.exception.util;

import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;

import com.inclever.library.logging.LogManagerFactory;

public class OrderedProperties {

    private static Logger log = LogManagerFactory.getInstance().getLogger(OrderedProperties.class);

    private Map<String, String> map = new LinkedHashMap<String, String>();

    public int size() {
        return map.entrySet().size();
    }

    public String getProperty(String key) {
        return map.get(key);
    }

    public String put(String key, String value) {
        return map.put(key, value);
    }

    public Map<String, String> getProperties() {
        return Collections.unmodifiableMap(map);
    }

    public void clear() {
        map.clear();
    }

    public void merge(InputStream inputStream) {
        load(inputStream, false);
    }

    public void load(InputStream inputStream) {
        load(inputStream, true);
    }

    private void load(InputStream inputStream, boolean clear) {
        if (clear) {
            clear();
        }
        Scanner scanner = new Scanner(inputStream);
        try {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line != null && (line.trim().length() == 0 || line.trim().charAt(0) != '#')) {
                    processLine(line);
                }
            }
        } finally {
            scanner.close();
        }
    }

    private void processLine(String line) {
        String value = "";
        if (line != null) {
            int index = line.indexOf("=");
            if (index > 0) {
                String key = String.valueOf(line.substring(0, index)).trim();
                try {
                    value = String.valueOf(line.substring(index + 1)).trim();
                } catch (Exception e) {
                    log.trace("Line ignored: '{}'", line);
                }
                map.put(key, value);
            } else {
                log.trace("Line ignored: '{}'", line);
            }
        }
    }
}
