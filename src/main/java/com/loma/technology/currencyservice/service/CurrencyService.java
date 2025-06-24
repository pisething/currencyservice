package com.loma.technology.currencyservice.service;

import java.util.List;

import com.loma.technology.currencyservice.dto.CurrencyDTO;

public interface CurrencyService {

	void insert(CurrencyDTO currencyDTO);
	
	void deleteById(Long id);
	
	void update(Long id, CurrencyDTO currencyDTO);
	
	CurrencyDTO getById(Long id);
	
	List<CurrencyDTO> getAll();
}
