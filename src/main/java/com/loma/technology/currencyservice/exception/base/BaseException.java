package com.loma.technology.currencyservice.exception.base;

import lombok.Getter;
import lombok.Setter;

@Getter
public class BaseException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4185413222820070556L;
	/**
	 * 
	 */
	private String code;
	private ErrorMessage errorMessage;
	
	@Getter
	@Setter
	protected ErrorDetail errorDetail;
	
	protected BaseException(String code, ErrorMessage errorMessage) {
		super(errorMessage.getDescription());
		this.code = code;
		this.errorMessage = errorMessage;
	}
}
