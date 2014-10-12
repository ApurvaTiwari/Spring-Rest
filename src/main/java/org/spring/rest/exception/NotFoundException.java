package org.spring.rest.exception;

public class NotFoundException extends Exception {

    private static final String MESSAGE_FORMAT = "Resource Not Found With Id '%s'";

	public NotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public NotFoundException(String resourceId) {
		super(String.format(MESSAGE_FORMAT , resourceId));
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
