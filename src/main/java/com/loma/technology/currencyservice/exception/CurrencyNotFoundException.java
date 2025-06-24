package com.loma.technology.currencyservice.exception;

import lombok.Getter;

@Getter
public class CurrencyNotFoundException extends BaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3351556421650880768L;
	public CurrencyNotFoundException(Long id) {
		this.setCode(ErrorCode.CURRENCY_NOT_FOUND.getValue());
		this.setErrorMessage(new ErrorMessage("Currency Not Found", "Currency Not Found With ID : "+ id));
	}
	

}
