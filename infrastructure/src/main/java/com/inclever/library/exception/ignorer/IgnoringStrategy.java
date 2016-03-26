/*

 */

package com.inclever.library.exception.ignorer;

// defines strategy for ignoring class analysis
public interface IgnoringStrategy {

    public static final String IGNORING_STRATEGY_KEY = "ignore.strategy";

    public boolean ignore(String className);
}
