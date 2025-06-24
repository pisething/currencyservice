package com.loma.technology.currencyservice.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.loma.technology.currencyservice.dto.CurrencyDTO;
import com.loma.technology.currencyservice.dto.CurrencySearchCriteria;

public interface CurrencyService {

	void insert(CurrencyDTO currencyDTO);
	
	void deleteById(Long id);
	
	void update(Long id, CurrencyDTO currencyDTO);
	
	CurrencyDTO getById(Long id);
	
	List<CurrencyDTO> getAll();
	
	Page<CurrencyDTO> search(CurrencySearchCriteria searchCriteria, Pageable pageable);
}
