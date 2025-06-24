package com.loma.technology.currencyservice.exception.base;

import lombok.Data;

@Data
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
}
