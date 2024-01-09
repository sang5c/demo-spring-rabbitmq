package com.example.demospringrabbitmq.infrastructure;

import com.example.demospringrabbitmq.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {
}
