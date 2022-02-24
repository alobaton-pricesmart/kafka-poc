package com.co.kafkapoc.exception.handler;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ServerWebInputException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler
{
	private static final String CONFLICT_ERROR = "Conflicting record in database.";

	@ExceptionHandler(value = { ConstraintViolationException.class })
	public ResponseEntity<String> handleConstraintViolation(ConstraintViolationException e)
	{
		log.warn(CONFLICT_ERROR, e);
		return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(value = { ServerWebInputException.class })
	public ResponseEntity<String> handleWebInputException(ServerWebInputException e)
	{
		log.debug("API Input Error", e);
		return new ResponseEntity<>(e.getMessage(), e.getStatus());
	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<String> handleEverythingElse(Exception e)
	{
		log.error("API Error", e);
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
