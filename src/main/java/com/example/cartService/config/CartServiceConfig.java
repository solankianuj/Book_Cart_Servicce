package com.example.cartService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CartServiceConfig {
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
