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

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/currencies")
public class CurrencyController {
	
	private final CurrencyService currencyService;
	
	@PostMapping
	public ResponseEntity<Void> createCurrency(@RequestBody CurrencyDTO dto){
		currencyService.insert(dto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteCurrency(@PathVariable long id){
		currencyService.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Void> updateCurrency(@RequestBody CurrencyDTO dto, @PathVariable long id){
		currencyService.update(id,dto);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<CurrencyDTO> getById(@PathVariable long id){
		return ResponseEntity.ok(currencyService.getById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<CurrencyDTO>> getAllCurrency(){
		return ResponseEntity.ok(currencyService.getAll());
	}
	
	@GetMapping("search")
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
