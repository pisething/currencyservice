package com.loma.technology.currencyservice.exception;

import com.loma.technology.currencyservice.exception.base.BaseException;
import com.loma.technology.currencyservice.exception.base.ErrorCode;
import com.loma.technology.currencyservice.exception.base.ErrorMessage;

import lombok.Getter;

@Getter
public class CurrencyNotFoundException extends BaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3351556421650880768L;
	public CurrencyNotFoundException(Long id) {
		super(
				ErrorCode.CURRENCY_NOT_FOUND.getValue(),
				new ErrorMessage("Currency Not Found", "Currency Not Found With ID : "+ id)
		);
	}
	

}
