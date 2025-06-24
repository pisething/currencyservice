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

@Mapper
public interface CurrencyRepository {

	@Select("SELECT * FROM currencies WHERE id = #{id}")
    Optional<Currency> findById(Long id);

    @Select("SELECT * FROM currencies")
    List<Currency> findAll();

    @Insert("INSERT INTO currencies(code, name, status) VALUES(#{code}, #{name}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Currency currency);

    @Update("UPDATE currencies SET name=#{name}, code=#{code}, status=#{status} WHERE id=#{id}")
    void update(Currency currency);

    @Delete("DELETE FROM currencies WHERE id = #{id}")
    void deleteById(Long id);
    
    @SelectProvider(type = CurrencyQueryProvider.class, method = "buildSearchQuery")
    List<Currency> search( @Param("criteria") CurrencySearchCriteria criteria, @Param("offset") int offset, @Param("limit") int limit);
    
    @SelectProvider(type = CurrencyQueryProvider.class, method = "buildCountQuery")
    long count( @Param("criteria") CurrencySearchCriteria criteria);
    
    
}
