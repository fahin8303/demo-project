package com.demo.exception;

import org.springframework.http.HttpStatus;
//import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.demo.domain.Response;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author fahin.ansari
 *
 *         Controller advice class, who allows us to consolidate our multiple,
 *         scattered @ExceptionHandlers from before into a single, global error
 *         handling component.
 */
@Slf4j
@ControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(value = Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	Response handleException(Throwable e) {
		log.error("Application Exception : {}", e);

		Response response = Response.getFailureResponse(ErrorCode.APPLICATION_ERROR);
		response.setMsg(e.getMessage());
		return response;
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	Response handleException(MethodArgumentNotValidException e) {
		log.error("Validation Exception : {}", e.getMessage());

		Response response = Response.getFailureResponse(ErrorCode.VALIDATION_OR_PARSING_ERROR);
		String error = e.getBindingResult().getFieldError().getField() + " "
				+ e.getBindingResult().getFieldError().getDefaultMessage();
		response.setMsg(error);
		return response;
	}

	@ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	Response handleException(MethodArgumentTypeMismatchException e) {
		log.error("Validation Exception : {}", e.getMessage());

		Response response = Response.getFailureResponse(ErrorCode.VALIDATION_OR_PARSING_ERROR);
		String error = e.getName() + " invalid";
		response.setMsg(error);
		return response;
	}

	@ExceptionHandler(value = BindException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	Response handleException(BindException e) {
		log.error("Validation Exception : {}", e.getMessage());

		Response response = Response.getFailureResponse(ErrorCode.VALIDATION_OR_PARSING_ERROR);
		String error = e.getBindingResult().getFieldError().getField() + " "
				+ e.getBindingResult().getFieldError().getDefaultMessage();
		response.setMsg(error);
		return response;
	}

	@ExceptionHandler(value = AppException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	Response handleException(AppException e) {
		log.error("Matrices Exception : {} : {}", e.getCode(), e.getMessage());
		return Response.getFailureResponse(e.getCode(), e.getMessage());
	}
}
