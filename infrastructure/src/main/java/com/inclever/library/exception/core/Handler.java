/*

 */

package com.inclever.library.exception.core;

import com.inclever.library.exception.populator.Populator;

public interface Handler {

    // exception handling strategy
    public Throwable handle(Throwable throwable, Object... params);

    // populator used for this Handler
    public Populator getPopulator();
}
