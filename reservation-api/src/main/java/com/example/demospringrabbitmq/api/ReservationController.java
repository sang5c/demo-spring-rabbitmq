package com.example.demospringrabbitmq.api;

import com.example.demospringrabbitmq.application.ReservationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(final ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/api/v1/reservation/cancel")
    public void cancel(long reservationId) {
        reservationService.cancel(reservationId);
    }
}
