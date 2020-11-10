package com.demo.exception;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final ErrorCode code;
	private final String message;

	public AppException(ErrorCode code) {
		super();
		this.code = code;
		this.message = code.getMessage();
	}

	public AppException(ErrorCode code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

}
