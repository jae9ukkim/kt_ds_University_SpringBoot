package com.ktdsuniversity.edu.config;

public class HelloSpringApiException extends RuntimeException {

	private static final long serialVersionUID = -7628298913600934699L;

	private int errorStatus;
	private Object error;
	
	public HelloSpringApiException(String message, int errorStatus, Object error) {
		super(message);
		this.errorStatus = errorStatus;
		this.error = error;
	}
	
	public int getErrorStatus() {
		return this.errorStatus;
	}
	public Object getError() {
		return this.error;
	}
	
}
