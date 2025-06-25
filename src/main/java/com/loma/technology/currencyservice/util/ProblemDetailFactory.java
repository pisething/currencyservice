package com.loma.technology.currencyservice.util;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.loma.technology.currencyservice.exception.base.BaseException;
import com.loma.technology.currencyservice.exception.base.ErrorCode;
import com.loma.technology.currencyservice.exception.base.ErrorDetail;

/**
 * Factory class to build standardized {@link ProblemDetail} objects 
 * used for error responses across the application.
 */
@Component
public class ProblemDetailFactory {
	
	/**
     * Creates a ProblemDetail from a custom BaseException with structured error information.
     *
     * @param httpStatus the HTTP status to be set in the response
     * @param ex the custom BaseException containing code and detailed message
     * @return a ProblemDetail object with structured fields
     */
	public ProblemDetail create(HttpStatus httpStatus, BaseException ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatus(httpStatus);
		problemDetail.setTitle(httpStatus.getReasonPhrase());
		problemDetail.setDetail(ex.getMessage());
		problemDetail.setProperty("code", ex.getCode());
		problemDetail.setProperty("message", ex.getErrorMessage());
		return problemDetail;
	}
	
	public ProblemDetail create(HttpStatus httpStatus, MethodArgumentNotValidException ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatus(httpStatus);
		
		
		Map<String, String> mapDetail = ex.getBindingResult().getFieldErrors().stream()
			.collect(Collectors.toMap(error -> error.getField(), error -> error.getDefaultMessage()));

		ErrorDetail errorDetail = new ErrorDetail("Validation Error", mapDetail);
				
		problemDetail.setTitle(httpStatus.getReasonPhrase());
		problemDetail.setDetail(ex.getMessage());
		problemDetail.setProperty("code", ErrorCode.VALIDATION_FAIL.getValue());
		problemDetail.setProperty("message", "Validation Error");
		problemDetail.setProperty("error_detail", errorDetail);
		return problemDetail;
	}
	
	/**
     * Creates a simple ProblemDetail with custom title and message.
     * Useful for generic/unexpected exceptions.
     *
     * @param httpStatus the HTTP status to be set in the response
     * @param title the short title describing the error
     * @param message the detailed message of the error
     * @return a ProblemDetail object with basic fields
     */
	public ProblemDetail create(HttpStatus httpStatus, String title, String message) {
		ProblemDetail problemDetail = ProblemDetail.forStatus(httpStatus);
		problemDetail.setTitle(title);
		problemDetail.setDetail(message);
		return problemDetail;
	}

}
