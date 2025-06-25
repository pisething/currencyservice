package com.loma.technology.currencyservice.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for transferring Currency data
 * between layers (e.g., controller <-> service <-> persistence).
 * 
 * Implements Serializable for safe transmission over the network
 * or storing in cache.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyDTO implements Serializable{
	private static final long serialVersionUID = 2750449976355815417L;
	
	/** Unique identifier for the currency */
	private Long id;
	
	/** ISO code or short code representing the currency (e.g., USD, EUR) */
	@NotBlank(message = "Currency code must not be blank")
    @Size(max = 10, message = "Currency code must be at most 10 characters")
	private String code;
	
	/** Full name of the currency (e.g., US Dollar, Euro) */
	@NotBlank(message = "Currency name must not be blank")
    @Size(max = 30, message = "Currency name must be at most 30 characters")
	private String name;
	
	/** Status of the currency (e.g., ACTIVE, INACTIVE) */
	@NotBlank(message = "Status must not be blank")
    @Size(max = 20, message = "Status must be at most 20 characters")
	private String status;
}
