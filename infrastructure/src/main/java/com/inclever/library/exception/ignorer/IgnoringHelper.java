/*

 */

package com.inclever.library.exception.ignorer;

import static com.inclever.library.exception.ignorer.IgnoringStrategy.IGNORING_STRATEGY_KEY;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

import com.inclever.library.exception.util.Utils;
import com.inclever.library.logging.LogManagerFactory;

public class IgnoringHelper {

	public static final Logger log = LogManagerFactory.getInstance().getLogger(IgnoringHelper.class);

	private static IgnoringStrategy strategy;
	private static Map<String, Boolean> classesToIgnore = Collections.synchronizedMap(new HashMap<String, Boolean>());

	static {
		// strategy used to ignoring classes
		String strategyClass = Utils.GLOBAL_PROPERTIES.getProperty(IGNORING_STRATEGY_KEY);

		if (strategyClass != null) {
			strategy = (IgnoringStrategy) Utils.createInstance(strategyClass);
		}

		// in case of problem, uses default strategy
		if (strategy == null) {
			strategy = new RegexIgnoringStrategy();
		}

		log.debug("Using '{}' for class ignoring analisys", strategy.getClass().getName());
	}

	public final static boolean ignoreClass(String className) {
		boolean ignore = true;

		// first, verify in cache
		if (!classesToIgnore.containsKey(className)) {
			ignore = strategy.ignore(className);
			if (ignore) {
				classesToIgnore.put(className, true);
			}
		}

		return ignore;
	}

}
