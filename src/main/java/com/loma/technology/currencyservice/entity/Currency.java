package com.loma.technology.currencyservice.entity;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Currency {
	private Long id;
	private String code;
	private String name;
	private String status;
}
