package com.loma.technology.currencyservice.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.loma.technology.currencyservice.util.ProblemDetailFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Global exception handler to handle and format exceptions thrown across the application.
 * It uses ProblemDetail (RFC 7807) for standardized error responses.
 */
@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler {
	// Factory class used to construct standardized ProblemDetail objects
	private final ProblemDetailFactory problemDetailFactory;

	/**
     * Handles CurrencyNotFoundException and returns a 404 NOT FOUND response.
     *
     * @param ex the exception thrown when a currency is not found
     * @return a ProblemDetail describing the error
     */
	@ExceptionHandler(CurrencyNotFoundException.class)
	public ProblemDetail handleCurrencyNotFound(CurrencyNotFoundException ex) {
		log.warn("Currency not found: {}", ex.getMessage());
		return problemDetailFactory.create(HttpStatus.NOT_FOUND, ex);
	}
	
	/**
	 * Handles validation exceptions thrown when a request body fails bean validation.
	 * This method is triggered when @Valid fails in controller methods.
	 *
	 * @param ex the exception containing validation errors
	 * @return a ProblemDetail object with HTTP 400 status and detailed validation messages
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ProblemDetail handleValidationException(MethodArgumentNotValidException ex) {
	    String detail = ex.getBindingResult().getFieldErrors().stream()
	            .map(error -> error.getField() + ": " + error.getDefaultMessage())
	            .collect(Collectors.joining(", "));
	    
	    log.warn("Validation failed: {}", detail);
	    return problemDetailFactory.create(HttpStatus.BAD_REQUEST, "Validation Failed", detail);
	}
	
	/**
     * Handles any unexpected exceptions and returns a 500 INTERNAL SERVER ERROR response.
     *
     * @param ex the exception thrown
     * @return a generic ProblemDetail response for internal errors
     */
	@ExceptionHandler(Exception.class)
	public ProblemDetail handleGenericException(Exception ex) {
		log.error("Unhandled error: {}", ex.getMessage(), ex);
		return problemDetailFactory.create(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server error", "An unexpected error occurred");
	}
}
