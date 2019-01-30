package com.skytest.exception;

public class TechnicalFailureException extends Exception {

	public TechnicalFailureException(String message) {
		super(message);
	}

	public TechnicalFailureException(Throwable cause) {
		super(cause);
	}

	public TechnicalFailureException(String message, Throwable cause) {
		super(message, cause);
	}

	public TechnicalFailureException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
