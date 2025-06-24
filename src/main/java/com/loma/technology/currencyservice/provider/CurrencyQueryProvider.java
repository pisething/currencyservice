package com.loma.technology.currencyservice.provider;

import java.util.Map;

import org.springframework.util.StringUtils;

import com.loma.technology.currencyservice.dto.CurrencySearchCriteria;

public class CurrencyQueryProvider {
	
	public String buildSearchQuery(Map<String, Object> params) {
		
		CurrencySearchCriteria criteria = (CurrencySearchCriteria) params.get("criteria");
		StringBuilder sql = new StringBuilder("SELECT * FROM currencies WHERE 1=1 ");
		
		if(StringUtils.hasText(criteria.getCode())) {
			sql.append(" AND code LIKE CONCAT('%',#{criteria.code},'%')");
		}
		
		if(StringUtils.hasText(criteria.getStatus())) {
			sql.append(" AND status = #{criteria.status}");
		}
		
		sql.append(" ORDER BY id DESC LIMIT #{limit} OFFSET #{offset}");
		
		return sql.toString();
	}
	
	public String buildCountQuery(Map<String, Object> params) {
		CurrencySearchCriteria criteria = (CurrencySearchCriteria) params.get("criteria");
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM currencies WHERE 1=1 ");
		
		if(StringUtils.hasText(criteria.getCode())) {
			sql.append(" AND code LIKE CONCAT('%',#{criteria.code},'%')");
		}
		
		if(StringUtils.hasText(criteria.getStatus())) {
			sql.append(" AND status = #{criteria.status}");
		}
		
		return sql.toString();
	}

}
