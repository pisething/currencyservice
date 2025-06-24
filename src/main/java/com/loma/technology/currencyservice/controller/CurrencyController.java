package com.loma.technology.currencyservice.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loma.technology.currencyservice.dto.CurrencyDTO;
import com.loma.technology.currencyservice.dto.CurrencySearchCriteria;
import com.loma.technology.currencyservice.service.CurrencyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/currencies")
public class CurrencyController {
	
	private final CurrencyService currencyService;
	
	@PostMapping
	@Operation(summary = "Create a new Currency")
	@ApiResponse(responseCode = "201", description = "Currency created")
	public ResponseEntity<Void> createCurrency(@RequestBody CurrencyDTO dto){
		currencyService.insert(dto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@DeleteMapping("{id}")
	@Operation(summary = "Delete a new Currency by ID")
	@ApiResponse(responseCode = "204", description = "Currency deleted")
	public ResponseEntity<Void> deleteCurrency(@PathVariable long id){
		currencyService.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PutMapping("{id}")
	@Operation(summary = "Update an existing Currency by ID")
	@ApiResponse(responseCode = "201", description = "Currency updated")
	public ResponseEntity<Void> updateCurrency(@RequestBody CurrencyDTO dto, @PathVariable long id){
		currencyService.update(id,dto);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("{id}")
	@Operation(summary = "Get a Currency by ID")
	@ApiResponse(responseCode = "200", description = "Currency retrieved")
	public ResponseEntity<CurrencyDTO> getById(@PathVariable long id){
		return ResponseEntity.ok(currencyService.getById(id));
	}
	
	@GetMapping
	@Operation(summary = "Get all Currencies")
	@ApiResponse(responseCode = "200", description = "List of currencies retrieved")
	public ResponseEntity<List<CurrencyDTO>> getAllCurrency(){
		return ResponseEntity.ok(currencyService.getAll());
	}
	
	@GetMapping("search")
	@Operation(summary = "Search currencies with filters and pagiantion")
	@ApiResponse(responseCode = "200", description = "Currency created")
	public ResponseEntity<Page<CurrencyDTO>> searchCurrency(
			@RequestParam(required = false) String code,
			@RequestParam(required = false) String status,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size
			){
		
		Pageable pageable = PageRequest.of(page, size);
		CurrencySearchCriteria criteria = new CurrencySearchCriteria();
		criteria.setCode(code);
		criteria.setStatus(status);
		
		return ResponseEntity.ok(currencyService.search(criteria, pageable));
	} 

}
