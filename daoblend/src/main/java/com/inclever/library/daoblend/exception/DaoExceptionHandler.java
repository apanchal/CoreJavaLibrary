package com.inclever.library.daoblend.exception;

import org.slf4j.Logger;

import com.inclever.library.exception.core.AbstractExceptionHandler;
import com.inclever.library.exception.core.Handler;
import com.inclever.library.exception.populator.Populator;
import com.inclever.library.logging.LogManagerFactory;

public class DaoExceptionHandler extends AbstractExceptionHandler implements Handler {

	private final Logger logger = LogManagerFactory.getInstance().getLogger(DaoExceptionHandler.class);

	public Throwable handle(Throwable throwable, Object... params) {
		logger.error(getExceptionLog(throwable));
		return throwable;
	}

	public Throwable handle(DaoException daoAccessException, Object... params) {
		logger.error(getExceptionLog(daoAccessException));
		daoAccessException.setHasBeenLogged(true);
		return daoAccessException;
	}

	public Throwable handle(DaoRuntimeException daoRuntimeException, Object... params) {
		logger.error(daoRuntimeException.getMessage());
		daoRuntimeException.setHasBeenLogged(true);
		return daoRuntimeException;
	}

	public Populator getPopulator() {
		return null;
	}

}
