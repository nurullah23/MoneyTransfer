package com.nurullah.moneytransfer.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ResultDTO implements Serializable {
	private static final long serialVersionUID = 6245464769171181322L;
	
	private Object result;
	private String errorMessage;
	
	private ResultDTO(Object result, String errorMessage) {
		this.result = result;
		this.errorMessage = errorMessage;
	}
	
	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public static ResultDTO from(Object result) {
		return new ResultDTO(result, null);
	}

	public static ResultDTO error(String errorMessage) {
		return new ResultDTO(null, errorMessage);
	}
}
