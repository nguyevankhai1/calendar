package com.example.bean;
import lombok.Data;

@Data
public class ResponseBean {
	private String statusCode = "200";
	private String message;
	private Object data;

	public ResponseBean() {
		// TODO Auto-generated constructor stub
	}

	public ResponseBean(String statusCode, String message, Object data) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
	}

}