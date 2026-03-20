package com.dscatalog.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dscatalog.controller.exceptions.StandardError;
import com.dscatalog.controller.exceptions.ValidationError;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandle {
	
	@ExceptionHandler(ControllerNotFoundException.class)
	public ResponseEntity<StandardError>entityNotFound(ControllerNotFoundException e, HttpServletRequest request){
		StandardError err= new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError("Resource not found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError>validation(MethodArgumentNotValidException e, HttpServletRequest request){
		ValidationError err= new ValidationError();
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
		err.setError("Validation exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		for (FieldError f  : e.getBindingResult().getFieldErrors()) {
			err.addErrros( f.getField() , f.getDefaultMessage() );
		}
		
		return ResponseEntity.status(status).body(err);
	}
}
