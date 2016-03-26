/*

 */

package com.inclever.library.exception.populator;

import com.inclever.library.exception.core.Handler;

// responsible for handler properties' population
public interface Populator {

    /**
     * @param handler
     *            handler used in class/method
     */
    public void populate(Handler handler);

    public String getProperty(String key);
}
