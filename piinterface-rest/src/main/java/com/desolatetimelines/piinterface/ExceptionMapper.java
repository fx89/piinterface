package com.desolatetimelines.piinterface;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.desolatetimelines.piinterface.model.CustomErrorResponse;

@RestControllerAdvice
public class ExceptionMapper {
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public CustomErrorResponse mapException(Exception npEx) {
		npEx.printStackTrace();
		return new CustomErrorResponse(500, npEx.getClass().getSimpleName(), npEx.getMessage());
	}

}
