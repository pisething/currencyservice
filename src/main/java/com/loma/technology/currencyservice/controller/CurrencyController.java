package com.loma.technology.currencyservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loma.technology.currencyservice.dto.CurrencyDTO;
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

}
