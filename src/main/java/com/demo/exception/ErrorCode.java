package com.demo.exception;

public enum ErrorCode {

	APPLICATION_ERROR(500, "Internal Server Error"), FORBIDDEN(403, "Forbidden"),
	VALIDATION_OR_PARSING_ERROR(400, "Validation or Parsing Error"), UNAUTHORIZED(401, "Unauthorized");

	private int code;
	private String message;

	ErrorCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
