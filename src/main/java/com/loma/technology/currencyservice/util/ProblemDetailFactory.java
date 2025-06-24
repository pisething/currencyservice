package com.loma.technology.currencyservice.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;

import com.loma.technology.currencyservice.exception.base.BaseException;

@Component
public class ProblemDetailFactory {
	
	public ProblemDetail create(HttpStatus httpStatus, BaseException ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatus(httpStatus);
		problemDetail.setTitle("Currency Not Found");
		problemDetail.setProperty("code", ex.getCode());
		problemDetail.setProperty("message", ex.getErrorMessage());
		return problemDetail;
	}

}
