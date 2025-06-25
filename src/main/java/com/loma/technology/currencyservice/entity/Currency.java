package com.loma.technology.currencyservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Represents the Currency entity mapped to the 'currencies' table in the database.
 * Includes fields for id, code, name, and status.
 * 
 */
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Currency {
	/**
     * Unique identifier for the currency (Primary Key).
     */
	private Long id;
	
	/**
     * Currency code (e.g., USD, EUR).
     */
	private String code;
	
	/**
     * Human-readable name of the currency (e.g., US Dollar).
     */
	private String name;
	
	/**
     * Status of the currency (e.g., ACTIVE, INACTIVE).
     */
	private String status;
}
