package com.example.bean;

import lombok.Data;

@Data
public class ResponseError {

	private Integer rowNumber;
	private Object column;
	private Object valueError;
	private String messageError;
	private String codeError;

	public ResponseError() {
	}

	public ResponseError(Integer rowNumber, String column, Object valueError, String messageError) {
		this.rowNumber = rowNumber;
		this.column = column;
		this.valueError = valueError;
		this.messageError = messageError;
	}

	public ResponseError(Integer rowNumber, Object column, Object valueError, String messageError, String codeError) {
		this.rowNumber = rowNumber;
		this.column = column;
		this.valueError = valueError;
		this.messageError = messageError;
		this.codeError = codeError;
	}
}