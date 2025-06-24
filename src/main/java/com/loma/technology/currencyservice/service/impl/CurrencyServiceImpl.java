package com.loma.technology.currencyservice.service.impl;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loma.technology.currencyservice.dto.CurrencyDTO;
import com.loma.technology.currencyservice.dto.CurrencySearchCriteria;
import com.loma.technology.currencyservice.entity.Currency;
import com.loma.technology.currencyservice.exception.CurrencyNotFoundException;
import com.loma.technology.currencyservice.mapper.CurrencyMapper;
import com.loma.technology.currencyservice.repository.CurrencyRepository;
import com.loma.technology.currencyservice.service.CurrencyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CurrencyServiceImpl implements CurrencyService{
	private final CurrencyRepository currencyRepository;
	private final CurrencyMapper currencyMapper;

	@Override
	@Transactional
	public void insert(CurrencyDTO currencyDTO) {
		Currency currency = currencyMapper.toCurrency(currencyDTO);
		currencyRepository.insert(currency);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		currencyRepository.findById(id)
		.ifPresentOrElse(existing -> {
			currencyRepository.deleteById(id);
		}, () -> {throw new CurrencyNotFoundException(id);});
		
	}

	@Override
	@Transactional
	public void update(Long id, CurrencyDTO currencyDTO) {
		currencyRepository.findById(id)
			.ifPresentOrElse(existing -> {
				currencyMapper.update(currencyDTO, existing);
				currencyRepository.update(existing);
			}, () -> {throw new CurrencyNotFoundException(id);});
	}

	@Override
	@Cacheable(value = "currencies", key = "#id")
	public CurrencyDTO getById(Long id) {
		return currencyRepository.findById(id)
				.map(currencyMapper::toCurrencyDTO)
				.orElseThrow(() -> new CurrencyNotFoundException(id));
	}

	@Override
	public List<CurrencyDTO> getAll() {
		return currencyRepository.findAll()
				.stream()
				.map(currencyMapper::toCurrencyDTO)
				.toList();
	}

	@Override
	public Page<CurrencyDTO> search(CurrencySearchCriteria searchCriteria, Pageable pageable) {
		int offset = (int) pageable.getOffset();
		int limit = pageable.getPageSize();
		
		List<Currency> currencies = currencyRepository.search(searchCriteria, offset, limit);
		long count = currencyRepository.count(searchCriteria);
		
		List<CurrencyDTO> dtoList = currencies.stream()
			.map(currencyMapper::toCurrencyDTO)
			.toList();
		
		return new PageImpl<>(dtoList, pageable, count);
	}

}
