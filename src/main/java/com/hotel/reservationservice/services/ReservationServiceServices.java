package com.hotel.reservationservice.services;

import com.hotel.reservationservice.entities.Reservation;
import com.hotel.reservationservice.entities.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;

@Service
public class ReservationServiceServices {

    @Autowired
    WebClient.Builder webClientBuilder;
    @Autowired
    private ReservationRepository reservationRepository;

    public String createReservation(String roomTypeName, Reservation reservation) {

        String roomId =null;
        String roomRent =null;
        reservation.setId(Long.parseLong(String.valueOf((int)(Math.random()*100000))));

        String response = webClientBuilder.build().post()
                    .uri("http://localhost:8072/hotelmanage-service/api/v1/book/room/"+roomTypeName+"/"+reservation.getNoOfPersons())
                .retrieve().bodyToMono(String.class).block();

        if(response !=null && !response.isEmpty()){
            roomId = response.split("||")[0];
            roomRent = response.split("||")[1];
        }

        reservation.setRoomId(roomId);
        assert roomRent != null;
        reservation.setTotalCost(new BigDecimal(roomRent));

        reservationRepository.save(reservation);

        return "Reservation Done with reservation Id : "+reservation.getId() + " for room Id : "+ roomId;
    }

    public String cancelReservation(Long reservationId) {

        Reservation reservation = reservationRepository.findById(reservationId).get();

        String response = webClientBuilder.build().post()
                .uri("http://localhost:8072/hotelmanage-service/api/v1/room/cancel/"+reservation.getRoomId())
                .retrieve().bodyToMono(String.class).block();

        reservationRepository.updateStatusById("CANCEL",reservationId);

        return "Booking Cancelled"+response;
    }

    public String checkOutReservation(String userName,Long reservationId,String paymentType) {

        Reservation reservation = reservationRepository.findById(reservationId).get();
            reservationRepository.updateStatusById("CHECKOUT",reservationId);
        String response = webClientBuilder.build().post()
                .uri("http://localhost:8072/hotelmanage-service/api/v1/room/checkout/"
                        +userName+"/"+reservationId+"/"+reservation.getRoomId())
                .retrieve().bodyToMono(String.class).block();

        String resp = webClientBuilder.build().post()
                .uri("http://localhost:8072/payment-service/api/v1/make/payment/"
                +userName+"/"+reservationId+"/"+reservation.getTotalCost()+"/"+paymentType)
                .retrieve().bodyToMono(String.class).block();


        return response+ " and "+ resp;
    }
}
