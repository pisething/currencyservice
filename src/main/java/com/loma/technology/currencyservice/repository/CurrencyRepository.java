package com.loma.technology.currencyservice.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.loma.technology.currencyservice.dto.CurrencySearchCriteria;
import com.loma.technology.currencyservice.entity.Currency;
import com.loma.technology.currencyservice.provider.CurrencyQueryProvider;

/**
 * MyBatis repository interface for CRUD and dynamic search operations
 * on the `currencies` table.
 */
@Mapper
public interface CurrencyRepository {

	/**
     * Find currency by ID.
     * 
     * @param id the currency ID
     * @return Optional containing Currency if found, otherwise empty
     */
	@Select("SELECT * FROM currencies WHERE id = #{id}")
    Optional<Currency> findById(Long id);

	/**
     * Retrieve all currencies.
     * 
     * @return List of all currencies
     */
    @Select("SELECT * FROM currencies")
    List<Currency> findAll();

    /**
     * Insert a new currency.
     * 
     * @param currency the Currency entity
     */
    @Insert("INSERT INTO currencies(code, name, status) VALUES(#{code}, #{name}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Currency currency);

    /**
     * Update an existing currency.
     * 
     * @param currency the Currency entity with updated values
     */
    @Update("UPDATE currencies SET name=#{name}, code=#{code}, status=#{status} WHERE id=#{id}")
    void update(Currency currency);

    /**
     * Delete currency by ID.
     * 
     * @param id the currency ID
     */
    @Delete("DELETE FROM currencies WHERE id = #{id}")
    void deleteById(Long id);
    
    /**
     * Perform dynamic search based on criteria (code, status) with pagination.
     * SQL is provided by {@link CurrencyQueryProvider}.
     *
     * @param criteria search criteria
     * @param offset   page offset
     * @param limit    number of items per page
     * @return list of matched currencies
     */
    @SelectProvider(type = CurrencyQueryProvider.class, method = "buildSearchQuery")
    List<Currency> search( @Param("criteria") CurrencySearchCriteria criteria, @Param("offset") int offset, @Param("limit") int limit);
    
    /**
     * Count total number of currencies matching the search criteria.
     * SQL is provided by {@link CurrencyQueryProvider}.
     *
     * @param criteria search criteria
     * @return total number of matched records
     */
    @SelectProvider(type = CurrencyQueryProvider.class, method = "buildCountQuery")
    long count( @Param("criteria") CurrencySearchCriteria criteria);
    
    
}
