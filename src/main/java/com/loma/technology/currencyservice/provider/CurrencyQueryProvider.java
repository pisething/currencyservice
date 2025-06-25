package com.loma.technology.currencyservice.provider;

import java.util.Map;

import org.springframework.util.StringUtils;

import com.loma.technology.currencyservice.dto.CurrencySearchCriteria;
/**
 * Provides dynamic SQL queries for Currency search and count operations.
 * Used with MyBatis @SelectProvider for building flexible queries based on input criteria.
 */
public class CurrencyQueryProvider {
	
	/**
     * Builds a dynamic SQL query to retrieve currencies based on search criteria.
     * Supports filtering by code (LIKE) and status (=), with pagination and ordering.
     *
     * @param params a map containing search criteria and pagination parameters
     * @return the constructed SQL SELECT statement
     */
	public String buildSearchQuery(Map<String, Object> params) {
		
		CurrencySearchCriteria criteria = (CurrencySearchCriteria) params.get("criteria");
		// Start base query
		StringBuilder sql = new StringBuilder("SELECT * FROM currencies WHERE 1=1 ");
		// Append dynamic filter: code LIKE '%code%'
		if(StringUtils.hasText(criteria.getCode())) {
			sql.append(" AND code LIKE CONCAT('%',#{criteria.code},'%')");
		}
		// Append dynamic filter: status = ?
		if(StringUtils.hasText(criteria.getStatus())) {
			sql.append(" AND status = #{criteria.status}");
		}
		// Add sorting and pagination
		sql.append(" ORDER BY id DESC LIMIT #{limit} OFFSET #{offset}");
		
		return sql.toString();
	}
	
	/**
     * Builds a dynamic SQL query to count currencies based on search criteria.
     * Supports the same filters as the search query but without pagination.
     *
     * @param params a map containing search criteria
     * @return the constructed SQL COUNT(*) statement
     */
	public String buildCountQuery(Map<String, Object> params) {
		CurrencySearchCriteria criteria = (CurrencySearchCriteria) params.get("criteria");
		// Start base count query
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM currencies WHERE 1=1 ");
		// Append dynamic filter: code LIKE '%code%'
		if(StringUtils.hasText(criteria.getCode())) {
			sql.append(" AND code LIKE CONCAT('%',#{criteria.code},'%')");
		}
		// Append dynamic filter: status = ?
		if(StringUtils.hasText(criteria.getStatus())) {
			sql.append(" AND status = #{criteria.status}");
		}
		
		return sql.toString();
	}

}
