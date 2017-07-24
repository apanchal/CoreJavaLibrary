package com.inclever.library.exception.test;

import org.junit.Test;

import com.inclever.library.exception.annotation.ExceptionHandler;
import com.inclever.library.exception.core.HandlerUtil;

public class ExceptionTest {

	@SuppressWarnings("null")
	@Test
	@ExceptionHandler(handler = ExceptionTestHandler.class)
	
	public void throwNullPointerException() {
		final String str = null;
		try {
			str.charAt(1);
		} catch (final Exception ex) {
			String errorParam[] = { "Employee.class", "1" };
			HandlerUtil.handle(ex, errorParam);

		}
	}

	@Test
	@ExceptionHandler(handler = ExceptionTestHandler.class)
	public void throwIndexOutOfBoundsException() {
		try {
			final String[] arr = {};
			@SuppressWarnings("unused")
			String s = arr[0];
		} catch (final Exception ex) {
			HandlerUtil.handle(ex);
		}
	}

	@Test
	@ExceptionHandler(handler = ExceptionTestHandler.class)
	public void throwRuntimeException() {
		try {
			throw new RuntimeException("This is Runtime Exception........");
		} catch (final Throwable ex) {
			// ex.printStackTrace();
			HandlerUtil.handle(ex);
		}
	}
}
