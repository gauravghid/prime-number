package com.example.prime.number.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI primeNumberSwagger()
    {
        return new OpenAPI()
                .info(new Info().title("REST API for fetching prime numbers"));
    }

}
