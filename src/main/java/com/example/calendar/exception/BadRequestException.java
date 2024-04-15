package com.example.calendar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6186495504484536489L;
	private final Object data;

	public BadRequestException(String message) {
		super(message);
		this.data = null;
	}

	public BadRequestException(String message, Object data) {
		super(message);
		this.data = data;
	}

	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
		this.data = null;
	}

	public Object getData() {
		return this.data;
	}

}