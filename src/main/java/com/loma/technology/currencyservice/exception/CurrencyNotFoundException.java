package com.loma.technology.currencyservice.exception;

import com.loma.technology.currencyservice.exception.base.BaseException;
import com.loma.technology.currencyservice.exception.base.ErrorCode;
import com.loma.technology.currencyservice.exception.base.ErrorMessage;

import lombok.Getter;

/**
 * Custom exception thrown when a currency with a given ID is not found.
 * Inherits from BaseException to include structured error code and message.
 */
@Getter
public class CurrencyNotFoundException extends BaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3351556421650880768L;
	/**
	 * Constructs a new CurrencyNotFoundException with the provided currency ID.
	 * It sets a custom error code and a descriptive error message.
	 *
	 * @param id the ID of the currency that was not found
	 */
	public CurrencyNotFoundException(Long id) {
		super(
				ErrorCode.CURRENCY_NOT_FOUND.getValue(),
				new ErrorMessage("Currency Not Found", "Currency Not Found With ID : "+ id)
		);
	}
	

}
