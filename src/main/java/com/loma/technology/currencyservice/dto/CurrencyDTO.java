package com.loma.technology.currencyservice.dto;

import lombok.Data;

@Data
public class CurrencyDTO {
	private Long id;
	private String code;
	private String name;
	private String status;
}
