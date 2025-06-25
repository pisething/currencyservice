package com.loma.technology.currencyservice.dto;

import lombok.Data;

/**
 * DTO for encapsulating filtering parameters used in
 * paginated currency search queries.
 */
@Data
public class CurrencySearchCriteria {
	
	/** Filter by status (e.g., ACTIVE, INACTIVE) */
	private String status;
	
	/** Filter by currency code (e.g., USD, EUR) */
	private String code;
}
