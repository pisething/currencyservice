package com.loma.technology.currencyservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.loma.technology.currencyservice.dto.CurrencyDTO;
import com.loma.technology.currencyservice.entity.Currency;

/**
 * MapStruct mapper interface for converting between Currency entity and CurrencyDTO.
 * This interface will be implemented automatically by MapStruct at compile time.
 */
@Mapper(componentModel = "spring")
public interface CurrencyMapper {

	/**
     * Converts a CurrencyDTO to a Currency entity.
     *
     * @param dto the source DTO
     * @return a mapped Currency entity
     */
	Currency toCurrency(CurrencyDTO dto);
	
	/**
     * Converts a Currency entity to a CurrencyDTO.
     *
     * @param entity the source Currency entity
     * @return a mapped CurrencyDTO
     */
	CurrencyDTO toCurrencyDTO(Currency entity);
	
	/**
     * Updates an existing Currency entity with data from CurrencyDTO.
     * Ignores the 'id' field to avoid overriding the primary key.
     *
     * @param dto the source DTO containing new values
     * @param entity the existing entity to be updated
     */
	@Mapping(target = "id", ignore = true)
	void update(CurrencyDTO dto,@MappingTarget Currency entity);
}
