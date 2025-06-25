package com.loma.technology.currencyservice.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for transferring Currency data
 * between layers (e.g., controller <-> service <-> persistence).
 * 
 * Implements Serializable for safe transmission over the network
 * or storing in cache.
 */
@Data
public class CurrencyDTO implements Serializable{
	private static final long serialVersionUID = 2750449976355815417L;
	
	/** Unique identifier for the currency */
	private Long id;
	
	/** ISO code or short code representing the currency (e.g., USD, EUR) */
	private String code;
	
	/** Full name of the currency (e.g., US Dollar, Euro) */
	private String name;
	
	/** Status of the currency (e.g., ACTIVE, INACTIVE) */
	private String status;
}
