package com.user.demo.dto;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ExceptionResponse extends RuntimeException{

	public int errorCode;
	public String errorMessage;
	public Date timestamp;
	
	public ExceptionResponse(int errorCode, String errorMessage, Date timestamp) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.timestamp = timestamp;
	}
	
	
	
}
