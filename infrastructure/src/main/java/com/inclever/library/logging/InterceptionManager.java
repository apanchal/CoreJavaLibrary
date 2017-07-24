package com.inclever.library.logging;

public final class InterceptionManager {

	private static InterceptionManager instance;

	private static boolean isEnableSOP = LogManagerFactory.getInstance().getCoreConfiguration().isEnableSOP();

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
		if (!isEnableSOP) {
			this.out = new SystemOutInterceptor(System.out);
			this.out.attachOut();

			this.err = new SystemOutInterceptor(System.err);
			this.err.attachErr();
		} else {
			System.out.println("Not intercepted, as its is disbaled in configuration");
		}
	}

}
