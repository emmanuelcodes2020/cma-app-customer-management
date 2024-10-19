package com.business.customermanagement.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.business.customermanagement.constants.ErrorConstant;
import com.business.customermanagement.dtos.ErrorResponse;

/**
 * The Class CustomerExceptionHandler - handles all exceptions from the applications
 */
@ControllerAdvice
public class CustomerExceptionHandler {
	
	/**
	 * Handles CustomerNotFoundException.
	 *
	 * @param notFoundException - exception of type CustomerNotFoundException
	 * @return the response entity with error code and message
	 */
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleCustomerNotFoundException
		(CustomerNotFoundException notFoundException) {
		ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now() ,notFoundException.getExceptionCode(), notFoundException.getExceptionMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles validation exception.
	 *
	 * @param validateException - exception of type MethodArgumentNotValidException
	 * @return the response entity with error code and message
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationExceptions(
			MethodArgumentNotValidException validateException) {
		ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(),
	    		ErrorConstant.BAD_REQUEST.getErrorCode(), 
	    		validateException.getBindingResult().getFieldErrors().stream().findFirst().get().getDefaultMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}

}
