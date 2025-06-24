package com.loma.technology.currencyservice.service;

import com.loma.technology.currencyservice.dto.CurrencyDTO;

public interface CurrencyService {

	void insert(CurrencyDTO currencyDTO);
	
	void deleteById(Long id);
	
	void update(Long id, CurrencyDTO currencyDTO);
}
