package com.loma.technology.currencyservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;

@Component
public class ProblemDetailFactory {
	
	ProblemDetail create(HttpStatus httpStatus, BaseException ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatus(httpStatus);
		problemDetail.setTitle("Currency Not Found");
		problemDetail.setProperty("code", ex.getCode());
		problemDetail.setProperty("message", ex.getErrorMessage());
		return problemDetail;
	}

}
