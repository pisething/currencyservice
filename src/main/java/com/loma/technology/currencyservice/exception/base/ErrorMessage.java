package com.loma.technology.currencyservice.exception.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorMessage {
	private final String title;
	private final String description;

}
