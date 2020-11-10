package com.demo.domain;

import java.io.Serializable;

import com.demo.exception.ErrorCode;

public class Response implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final int STATUS_SUCCESS = 200;
	private static final String SUCCESS = "Success";

	private int status;
	private String msg;
	private Object data;

	public Response() {
	}

	public Response(int status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}

	public static Response getSuccessResponse() {
		return new Response(STATUS_SUCCESS, SUCCESS);
	}

	public static Response getSuccessResponse(Object data) {
		Response response = getSuccessResponse();
		response.setData(data);
		return response;
	}

	public static Response getFailureResponse(ErrorCode error) {
		return new Response(error.getCode(), error.getMessage());
	}

	public static Response getFailureResponse(ErrorCode error, String message) {
		return new Response(error.getCode(), message);
	}

	public int getStatus() {
		return status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
