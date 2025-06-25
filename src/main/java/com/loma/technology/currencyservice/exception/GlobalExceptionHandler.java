package com.loma.technology.currencyservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.loma.technology.currencyservice.util.ProblemDetailFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler {
	
	private final ProblemDetailFactory problemDetailFactory;

	@ExceptionHandler(CurrencyNotFoundException.class)
	public ProblemDetail handleCurrencyNotFound(CurrencyNotFoundException ex) {
		log.warn("Currency not found: {}", ex.getMessage());
		return problemDetailFactory.create(HttpStatus.NOT_FOUND, ex);
	}
	
	@ExceptionHandler(Exception.class)
	public ProblemDetail handleGenericException(Exception ex) {
		log.error("Unhandled error: {}", ex.getMessage(), ex);
		return problemDetailFactory.create(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server error", "An unexpected error occurred");
	}
}
