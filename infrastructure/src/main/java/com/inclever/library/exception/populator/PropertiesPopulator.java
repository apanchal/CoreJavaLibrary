/*
 
 */

package com.inclever.library.exception.populator;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;

import com.inclever.library.exception.core.Handler;
import com.inclever.library.exception.util.OrderedProperties;
import com.inclever.library.exception.util.Utils;
import com.inclever.library.logging.LogManagerFactory;

public class PropertiesPopulator implements Populator {

    private static final Logger LOGGER = LogManagerFactory.getInstance().getLogger(PropertiesPopulator.class);

    private String propertiesFile;

    // private

    private OrderedProperties properties = new OrderedProperties();

    public PropertiesPopulator() {
        propertiesFile = "";
    }

    public PropertiesPopulator(String propertiesFile) {
        this.propertiesFile = propertiesFile;
    }

    public void populate(Handler handler) {
        if (propertiesFile == null || "".equals(propertiesFile.trim())) {
            // uses properties defined <handler_class_name>.properties
            propertiesFile = handler.getClass().getSimpleName() + ".properties";
        }

        if (!propertiesFile.endsWith(".properties")) {
            propertiesFile += ".properties";
        }
        String[] propertiesFiles = { propertiesFile };
        Map<String, Object> properties = beanProperties(handler, propertiesFiles);

        try {
            BeanUtils.populate(handler, properties);
        } catch (Exception e) {
            LOGGER.error("Error populating " + handler, e);
        }
    }

    private Map<String, Object> beanProperties(Handler handler, String[] propertiesFiles) {
        Map<String, Object> map = new HashMap<String, Object>();
        OrderedProperties properties = Utils.load(propertiesFiles);
        String beanClass = handler.getClass().getName();
        for (Entry<String, String> entry : properties.getProperties().entrySet()) {
            // property definition found
            String property = entry.getKey();

            // format: beanClass.property = value
            if (property.startsWith(beanClass)) {
                property = property.substring(beanClass.length() + 1);
            } else {
                // format: property = value
            }

            LOGGER.trace("Property definition found for {} [{}={}]", beanClass, property);

            Object value = instantiate(entry.getValue());

            map.put(property, value);
        }

        return map;
    }

    private Object instantiate(String value) {
        Object result = value;
        // property file is a class name?
        if (value.contains(".")) {
            try {
                Class<?> clazz = Utils.forName(value);
                result = clazz.newInstance();
                LOGGER.trace("Class '{}' instantiated: {}", value, result);
            } catch (InstantiationException e) {
                LOGGER.error("Can't instantiate class '" + value + "'", e);
            } catch (IllegalAccessException e) {
                LOGGER.error("Can't instantiate class '" + value + "'", e);
            }
        }
        return result;
    }

    // @Override
    public String getProperty(String key) {
        if (key != null) {
            return properties.getProperty(key);
        }
        return null;
    }
}
