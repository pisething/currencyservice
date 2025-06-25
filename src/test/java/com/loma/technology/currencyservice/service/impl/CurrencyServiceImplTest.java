package com.loma.technology.currencyservice.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.loma.technology.currencyservice.dto.CurrencyDTO;
import com.loma.technology.currencyservice.dto.CurrencySearchCriteria;
import com.loma.technology.currencyservice.entity.Currency;
import com.loma.technology.currencyservice.exception.CurrencyNotFoundException;
import com.loma.technology.currencyservice.mapper.CurrencyMapper;
import com.loma.technology.currencyservice.repository.CurrencyRepository;

class CurrencyServiceImplTest {

    @InjectMocks
    private CurrencyServiceImpl currencyService;

    @Mock
    private CurrencyRepository currencyRepository;

    @Mock
    private CurrencyMapper currencyMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() {
        CurrencyDTO dto = new CurrencyDTO(1L, "USD", "US Dollar", "ACTIVE");
        Currency entity = new Currency(1L, "USD", "US Dollar", "ACTIVE");

        when(currencyMapper.toCurrency(dto)).thenReturn(entity);

        currencyService.insert(dto);

        verify(currencyRepository).insert(entity);
    }

    @Test
    void testDeleteById_success() {
        Currency entity = new Currency(1L, "USD", "US Dollar", "ACTIVE");

        when(currencyRepository.findById(1L)).thenReturn(Optional.of(entity));

        currencyService.deleteById(1L);

        verify(currencyRepository).deleteById(1L);
    }

    @Test
    void testDeleteById_notFound() {
        when(currencyRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CurrencyNotFoundException.class, () -> currencyService.deleteById(1L));
    }

    @Test
    void testUpdate_success() {
        CurrencyDTO dto = new CurrencyDTO(1L, "USD", "US Dollar Updated", "ACTIVE");
        Currency existing = new Currency(1L, "USD", "US Dollar", "ACTIVE");

        when(currencyRepository.findById(1L)).thenReturn(Optional.of(existing));

        currencyService.update(1L, dto);

        verify(currencyMapper).update(dto, existing);
        verify(currencyRepository).update(existing);
    }

    @Test
    void testUpdate_notFound() {
        CurrencyDTO dto = new CurrencyDTO(1L, "USD", "US Dollar Updated", "ACTIVE");

        when(currencyRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CurrencyNotFoundException.class, () -> currencyService.update(1L, dto));
    }

    @Test
    void testGetById_success() {
        Currency entity = new Currency(1L, "USD", "US Dollar", "ACTIVE");
        CurrencyDTO dto = new CurrencyDTO(1L, "USD", "US Dollar", "ACTIVE");

        when(currencyRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(currencyMapper.toCurrencyDTO(entity)).thenReturn(dto);

        CurrencyDTO result = currencyService.getById(1L);
        assertEquals(dto, result);
    }

    @Test
    void testGetById_notFound() {
        when(currencyRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CurrencyNotFoundException.class, () -> currencyService.getById(1L));
    }

    @Test
    void testGetAll() {
        List<Currency> currencies = List.of(new Currency(1L, "USD", "US Dollar", "ACTIVE"));
        List<CurrencyDTO> dtos = List.of(new CurrencyDTO(1L, "USD", "US Dollar", "ACTIVE"));

        when(currencyRepository.findAll()).thenReturn(currencies);
        when(currencyMapper.toCurrencyDTO(currencies.get(0))).thenReturn(dtos.get(0));

        List<CurrencyDTO> result = currencyService.getAll();
        assertEquals(dtos, result);
    }

    @Test
    void testSearch() {
        CurrencySearchCriteria criteria = new CurrencySearchCriteria();
        Pageable pageable = PageRequest.of(0, 10);
        List<Currency> currencies = List.of(new Currency(1L, "USD", "US Dollar", "ACTIVE"));
        List<CurrencyDTO> dtos = List.of(new CurrencyDTO(1L, "USD", "US Dollar", "ACTIVE"));

        when(currencyRepository.search(criteria, 0, 10)).thenReturn(currencies);
        when(currencyRepository.count(criteria)).thenReturn(1L);
        when(currencyMapper.toCurrencyDTO(currencies.get(0))).thenReturn(dtos.get(0));

        var result = currencyService.search(criteria, pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals(dtos.get(0), result.getContent().get(0));
    }
}
