package com.loma.technology.currencyservice.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CurrencyDTO implements Serializable{
	private static final long serialVersionUID = 2750449976355815417L;
	private Long id;
	private String code;
	private String name;
	private String status;
}
