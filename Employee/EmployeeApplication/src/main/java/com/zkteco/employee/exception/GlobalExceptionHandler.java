package com.zkteco.employee.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@ResponseStatus
public class GlobalExceptionHandler {

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleEmployeeNotFoundException(EmployeeNotFoundException exception,
			WebRequest request) {

		ErrorMessage errorMessage = new ErrorMessage(new Date(), HttpStatus.NOT_FOUND, exception.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);

	}

}
