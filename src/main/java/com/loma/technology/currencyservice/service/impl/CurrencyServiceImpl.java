package com.loma.technology.currencyservice.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loma.technology.currencyservice.dto.CurrencyDTO;
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
	public void update(Long id, CurrencyDTO currencyDTO) {
		currencyRepository.findById(id)
			.ifPresentOrElse(existing -> {
				currencyMapper.update(currencyDTO, existing);
				currencyRepository.update(existing);
			}, () -> {throw new CurrencyNotFoundException(id);});
	}

	@Override
	public CurrencyDTO findById(Long id) {
		return currencyRepository.findById(id)
				.map(currencyMapper::toCurrencyDTO)
				.orElseThrow(() -> new CurrencyNotFoundException(id));
	}

}
