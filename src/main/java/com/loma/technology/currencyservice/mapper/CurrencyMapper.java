package com.loma.technology.currencyservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.loma.technology.currencyservice.dto.CurrencyDTO;
import com.loma.technology.currencyservice.entity.Currency;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {

	Currency toCurrency(CurrencyDTO dto);
	
	CurrencyDTO toCurrencyDTO(Currency entity);
	
	@Mapping(target = "id", ignore = true)
	void update(CurrencyDTO dto,@MappingTarget Currency entity);
}
