package com.hotel.reservationservice.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Transactional
    @Modifying
    @Query("update Reservation r set r.status = ?1 where r.id = ?2")
    int updateStatusById(String status, Long id);
}