/*
 */

package com.inclever.library.exception.ignorer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;

import com.inclever.library.exception.util.Utils;
import com.inclever.library.logging.LogManagerFactory;

public class RegexIgnoringStrategy implements IgnoringStrategy {

    private static final Logger LOGGER = LogManagerFactory.getInstance().getLogger(RegexIgnoringStrategy.class);

    public static final String IGNORING_KEY = "ignore";

    private List<String> regexList = new ArrayList<>();

    public RegexIgnoringStrategy() {
        populateRegex();
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Expressions used for ignoring classes:");
            for (String regex : regexList) {
                LOGGER.trace(regex);
            }
        }
    }

    /**
     * 
     */
    @Override
    public boolean ignore(String className) {
        boolean ignore = false;

        LOGGER.trace("-------> Analising '{}'", className);

        for (String regex : regexList) {
            // trace only
            String msg = "";
            if (LOGGER.isTraceEnabled()) {
                msg = "Matching '" + className + "' with '" + regex + "'";
            }

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(className);
            ignore = matcher.matches();

            // trace only
            if (LOGGER.isTraceEnabled()) {
                if (ignore) {
                    LOGGER.trace("{} [inspection ignored]", msg);
                } else {
                    LOGGER.trace("{} [inspection will be applied]", msg);
                }
            }

            // expression matches class name, we can stop
            if (ignore) {
                break;
            }
        }

        LOGGER.trace("<------- End of analisys");

        return ignore;
    }

    // read expressions from exception.properties
    private void populateRegex() {
        Map<String, String> expressions = Utils.GLOBAL_PROPERTIES.getProperties();
        for (Entry<String, String> entry : expressions.entrySet()) {
            String key = String.valueOf(entry.getKey());
            if (key.startsWith(IGNORING_KEY) && !key.equals(IGNORING_STRATEGY_KEY)) {
                String ignoreExpr = String.valueOf(entry.getValue()).trim();
                if (ignoreExpr.length() > 0) {
                    regexList.add(ignoreExpr);
                }
            }
        }
    }
}
