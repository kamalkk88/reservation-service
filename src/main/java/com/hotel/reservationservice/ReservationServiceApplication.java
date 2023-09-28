package com.hotel.reservationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ReservationServiceApplication {

    public static void main(String[] args) {

        new SpringApplicationBuilder()
                .profiles("dev").sources(ReservationServiceApplication.class)
                .run(args);
    }

}
