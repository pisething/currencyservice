package com.loma.technology.currencyservice.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.loma.technology.currencyservice.dto.CurrencyDTO;
import com.loma.technology.currencyservice.dto.CurrencySearchCriteria;

/**
 * Service interface for managing currency operations.
 * Defines the business logic methods for CRUD and search operations.
 */
public interface CurrencyService {

	/**
     * Inserts a new currency into the system.
     *
     * @param currencyDTO the data transfer object representing the new currency
     */
	void insert(CurrencyDTO currencyDTO);
	
	/**
     * Deletes a currency by its ID.
     *
     * @param id the ID of the currency to be deleted
     */
	void deleteById(Long id);
	
	/**
     * Updates an existing currency by its ID.
     *
     * @param id          the ID of the currency to update
     * @param currencyDTO the updated currency data
     */
	void update(Long id, CurrencyDTO currencyDTO);
	
	/**
     * Retrieves a single currency by its ID.
     *
     * @param id the ID of the currency to retrieve
     * @return the CurrencyDTO if found
     */
	CurrencyDTO getById(Long id);
	
	/**
     * Retrieves all currencies.
     *
     * @return a list of all CurrencyDTOs
     */
	List<CurrencyDTO> getAll();
	
	/**
     * Searches currencies based on dynamic criteria and pagination.
     *
     * @param searchCriteria filter criteria (code, status)
     * @param pageable       pagination information
     * @return a paginated result of matched CurrencyDTOs
     */
	Page<CurrencyDTO> search(CurrencySearchCriteria searchCriteria, Pageable pageable);
}
