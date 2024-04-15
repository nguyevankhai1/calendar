package com.example.calendar.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.bean.ResponseBean;
import com.example.bean.ResponseError;
import com.example.calendar.until.CommonFn;

@ControllerAdvice
public class ExceptionHandlerAdvice {

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
		ResponseBean rt = new ResponseBean();
		rt.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value() + "");
		rt.setMessage(ex.getMessage());
		return new ResponseEntity<Object>(rt, new HttpHeaders(), HttpStatus.OK);
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
			WebRequest request) {
		ResponseBean rt = new ResponseBean();
		rt.setStatusCode(HttpStatus.BAD_REQUEST.value() + "");
		BindingResult result = ex.getBindingResult();
		ResponseError err = new ResponseError();
		result.getFieldErrors().forEach(error -> {
			err.setValueError(error.getRejectedValue());
			err.setMessageError(error.getDefaultMessage());
		});
		ResponseError responseErrors = CommonFn.convertMessageSizrForEntity(err, result.getTarget(),
				result.getFieldError().getField());
		rt.setMessage(responseErrors.getMessageError());
		return new ResponseEntity<Object>(rt, new HttpHeaders(), HttpStatus.OK);
	}
	
	@ExceptionHandler({ BadRequestException.class })
	public ResponseEntity<Object> handleBadRequestException(BadRequestException ex, WebRequest request) {
		ResponseBean rt = new ResponseBean(HttpStatus.BAD_REQUEST.value() + "", ex.getMessage(), ex.getMessage());
		rt.setData(ex.getData());
		return new ResponseEntity<Object>(rt, new HttpHeaders(), HttpStatus.OK);
	}
}
