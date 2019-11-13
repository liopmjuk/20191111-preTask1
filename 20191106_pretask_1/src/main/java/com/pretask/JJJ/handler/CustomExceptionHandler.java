package com.pretask.JJJ.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.ResponseStatus;

import com.pretask.JJJ.Dto.Output.FailureOutput;
import com.pretask.JJJ.exception.CustomException;

@ControllerAdvice
public class CustomExceptionHandler {
	Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value=CustomException.class)
	public ResponseEntity<?> handleCustomException(CustomException ex) {
		logger.error("errorCode: {}, errorCode: {}", ex.getErrorCode(), ex.getErrorMsg());
		
		return new ResponseEntity(new FailureOutput(ex), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value=Exception.class)
	public ResponseEntity<?> handleAnyException(Exception ex) {
		logger.error("errorCode: {}", ex);
		
		return new ResponseEntity(new FailureOutput("","서버에서 오류가 발생했습니다."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value=MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidException(MethodArgumentNotValidException ex) {
		logger.error("errorCode: {}", ex);
		
		return new ResponseEntity(new FailureOutput("","필수입력 데이터를 확인해주세요."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}