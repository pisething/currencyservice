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
		log.info("Create a new currency: {}", currencyDTO.getCode());
		Currency currency = currencyMapper.toCurrency(currencyDTO);
		log.debug("Mapped currency entity: {}", currency);
		currencyRepository.insert(currency);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		log.info("Deleting currency with id={}", id);
		currencyRepository.findById(id)
		.ifPresentOrElse(existing -> {
			log.debug("Currency found for deletion: {}", existing);
			currencyRepository.deleteById(id);
		}, 
		() -> {
			log.warn("Attemp to delete non-existent currency id={}", id);
			throw new CurrencyNotFoundException(id);
		});
		
	}

	@Override
	@Transactional
	public void update(Long id, CurrencyDTO currencyDTO) {
		log.info("Updating currency with id={}", id);
		currencyRepository.findById(id)
			.ifPresentOrElse(existing -> {
				log.debug("Currency before update: {}", existing);
				currencyMapper.update(currencyDTO, existing);
				log.debug("Currency after update: {}", existing);
				currencyRepository.update(existing);
			}, 
			() -> {
				log.warn("Attemp to update non-existent currency id={}", id);
				throw new CurrencyNotFoundException(id);
			});
	}

	@Override
	@Cacheable(value = "currencies", key = "#id")
	public CurrencyDTO getById(Long id) {
		log.info("Fetching currency by id={}", id);
		return currencyRepository.findById(id)
				.map(currencyMapper::toCurrencyDTO)
				.orElseThrow(() -> {
					log.warn("Currency not found with id={}", id);
					throw new CurrencyNotFoundException(id);
				}); 
	}

	@Override
	public List<CurrencyDTO> getAll() {
		log.info("Fetching all currencies");
		var list = currencyRepository.findAll()
				.stream()
				.map(currencyMapper::toCurrencyDTO)
				.toList();
		log.debug("Total currencies fetched: {}", list.size());
		return list;
	}

	@Override
	public Page<CurrencyDTO> search(CurrencySearchCriteria searchCriteria, Pageable pageable) {
		log.info("Searching currencies with criteria: {}, page={}, size={}", searchCriteria, pageable.getPageNumber(), pageable.getPageSize());
		int offset = (int) pageable.getOffset();
		int limit = pageable.getPageSize();
		
		List<Currency> currencies = currencyRepository.search(searchCriteria, offset, limit);
		long count = currencyRepository.count(searchCriteria);
		
		List<CurrencyDTO> dtoList = currencies.stream()
			.map(currencyMapper::toCurrencyDTO)
			.toList();
		
		log.debug("Search returned {} items out of total={}", dtoList.size(), count);
		
		return new PageImpl<>(dtoList, pageable, count);
	}

}
