package com.zkteco.employee.exception;

public class EmployeeNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException() {
		super();

	}

	public EmployeeNotFoundException(String message) {
		super(message);
	}

	public EmployeeNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public EmployeeNotFoundException(String message, Throwable cause) {
		super(message, cause);

	}

	public EmployeeNotFoundException(Throwable cause) {
		super(cause);

	}

}
