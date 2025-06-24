package com.loma.technology.currencyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CurrencyserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyserviceApplication.class, args);
	}

}
