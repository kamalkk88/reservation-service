package com.hotel.reservationservice.controller;

import com.hotel.reservationservice.entities.Reservation;
import com.hotel.reservationservice.entities.ReservationRepository;
import com.hotel.reservationservice.services.ReservationServiceServices;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReservationServiceController {

    Logger logger = LoggerFactory.getLogger(ReservationServiceController.class);

    private final ReservationRepository reservationRepository;

    private final ReservationServiceServices reservationServiceServices;

    @GetMapping("/get/reservation/{reservationId}")
    public Reservation getReservation(@PathVariable("reservationId") Long reservationId){
        logger.info("Inside get of reservation details : ");
        return reservationRepository.findById(reservationId).get();
    }

    @PostMapping("/create/reservation/{roomTypeName}")
    public ResponseEntity<String> createReservation(@PathVariable("roomTypeName") String roomTypeName,
                                                    @RequestBody Reservation reservation){
        String resp = reservationServiceServices.createReservation(roomTypeName,reservation);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/cancel/reservation/{reservationId}")
    public ResponseEntity<String> cancelReservation(@PathVariable("reservationId") Long reservationId){

        logger.info("Inside Cancel Reservation");

        String resp = reservationServiceServices.cancelReservation(reservationId);

        return ResponseEntity.ok(resp);
    }

    @PostMapping("/room/checkout/{username}/{reservationId}/{paymentType}")
    public ResponseEntity<String> checkOutReservation(@PathVariable("username") String userName,
                                                      @PathVariable("reservationId") Long reservationId,
                                                      @PathVariable("paymentType") String paymentType){

        logger.info("Inside Checkout Reservation");

        String resp = reservationServiceServices.checkOutReservation(userName,reservationId,paymentType);

        return ResponseEntity.ok(resp);
    }
}






