package com.example.nienluannganh.exception;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import com.example.nienluannganh.objectcontroller.responseentity.response;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class exception {
	@ExceptionHandler(value=Exception.class)
	public ResponseEntity<response> exception(Exception e) {
		response r= new response(HttpStatus.BAD_REQUEST,e.getMessage(),null);
		e.printStackTrace();
		return new ResponseEntity(r,HttpStatus.OK);
	}
	
	@ExceptionHandler(value=ConstraintViolationException.class)
	public ResponseEntity<response> exception(ConstraintViolationException e) {
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		String firstMessage = "";
		if (!violations.isEmpty()) {
		    ConstraintViolation<?> firstViolation = violations.iterator().next();
		    firstMessage = firstViolation.getMessage();
		}
		response r = new response(HttpStatus.BAD_REQUEST, firstMessage, null);
		return new ResponseEntity<>(r, HttpStatus.OK);

	}
	
	@ExceptionHandler(value =MissingServletRequestParameterException.class)
	public ResponseEntity<response> MissingServletRequestParameterExceptionfunc(MissingServletRequestParameterException e){
		System.out.println(e.getClass());
		response r= new response(HttpStatus.BAD_REQUEST,e.getMessage(), null);
		return new ResponseEntity(r,HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<response> MissingServletRequestParameterExceptionfunc(EntityNotFoundException e){
		response r= new response(HttpStatus.BAD_REQUEST,e.getMessage(), null);
		return new ResponseEntity(r,HttpStatus.OK);
	}
	
	@ExceptionHandler(value =MethodArgumentNotValidException.class)
	public ResponseEntity<response> methodexxception(MethodArgumentNotValidException e) {
		response r= new response(HttpStatus.BAD_REQUEST,e.getBindingResult().getFieldError().getDefaultMessage(), null);
		return new ResponseEntity(r, HttpStatus.OK);
	}
	@ExceptionHandler(IOException.class)
	public ResponseEntity<response> MissingServletRequestParameterExceptionfunc(IOException e){
		response r= new response(HttpStatus.BAD_REQUEST,e.getMessage(), null);
		return new ResponseEntity(r,HttpStatus.OK);
	}
	
}
