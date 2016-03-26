package com.inclever.library.logging;

public final class InterceptionManager {

    private static InterceptionManager instance;

    /** out */
    private SystemOutInterceptor out;

    /** err */
    private SystemOutInterceptor err;

    static {
        instance = new InterceptionManager();
    }

    /**
     * Initializes a new instance of the class InterceptionManager.
     */
    private InterceptionManager() {
        super();
    }

    public static synchronized InterceptionManager getInstance() {
        if (instance == null) {
            instance = new InterceptionManager();
        }
        return instance;
    }

    public final void intercept() {
        this.out = new SystemOutInterceptor(System.out);
        this.out.attachOut();

        this.err = new SystemOutInterceptor(System.err);
        this.err.attachErr();
    }

}
