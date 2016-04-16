/*

 */

package com.inclever.library.exception.core;

public class HandlerUtil {

    private static HandlerHelper helper = new HandlerHelper();

    private HandlerUtil() {
    }

    public static final Throwable handle(Throwable throwable, Object... params) {
        // passes the error for the treatment of HandlerHelper
        return helper.handle(throwable, params);
    }

    public static final Handler currentHandler() {
        return helper.getHandler();
    }
}
