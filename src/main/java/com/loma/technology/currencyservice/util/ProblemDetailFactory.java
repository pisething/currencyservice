package com.loma.technology.currencyservice.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;

import com.loma.technology.currencyservice.exception.base.BaseException;

@Component
public class ProblemDetailFactory {
	
	public ProblemDetail create(HttpStatus httpStatus, BaseException ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatus(httpStatus);
		problemDetail.setTitle(httpStatus.getReasonPhrase());
		problemDetail.setDetail(ex.getMessage());
		problemDetail.setProperty("code", ex.getCode());
		problemDetail.setProperty("message", ex.getErrorMessage());
		return problemDetail;
	}
	
	public ProblemDetail create(HttpStatus httpStatus, String title, String message) {
		ProblemDetail problemDetail = ProblemDetail.forStatus(httpStatus);
		problemDetail.setTitle(title);
		problemDetail.setDetail(message);
		return problemDetail;
	}

}
