package com.hotel.reservationservice.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @Column(name = "reservation_id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "room_id", length = 50)
    private String roomId;

    @Column(name = "check_in_date", length = 10)
    private String checkInDate;

    @Column(name = "check_out_date", length = 10)
    private String checkOutDate;

    @Column(name = "reservation_date", length = 10)
    private String reservationDate;

    @Column(name = "no_of_persons", precision = 20)
    private BigDecimal noOfPersons;

    @Column(name = "total_cost", precision = 10)
    private BigDecimal totalCost;

    @Column(name= "room_type",length = 20)
    private String roomType;

    @Column(name = "status", length = 10)
    private String status;

}