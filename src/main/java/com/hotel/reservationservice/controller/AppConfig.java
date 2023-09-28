package com.hotel.reservationservice.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @Bean
    public WebClient.Builder loadBalancedWebClientBuilder(){
        return WebClient.builder();
    }
}
