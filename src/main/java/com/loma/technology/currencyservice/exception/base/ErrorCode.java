package com.loma.technology.currencyservice.exception.base;

import lombok.Getter;

@Getter
public enum ErrorCode {

	CURRENCY_NOT_FOUND("40001"),
	VALIDATION_FAIL("40002");
	
	private String value;
	
	private ErrorCode(String value) {
		this.value = value;
	}
}
