package com.example.prime.number;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EnableCaching
public class PrimeNumberApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrimeNumberApplication.class, args);
	}

}
