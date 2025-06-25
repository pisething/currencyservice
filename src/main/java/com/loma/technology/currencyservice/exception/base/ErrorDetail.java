package com.loma.technology.currencyservice.exception.base;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDetail {
	private String type;
	private Map<String, String> detail;

}
