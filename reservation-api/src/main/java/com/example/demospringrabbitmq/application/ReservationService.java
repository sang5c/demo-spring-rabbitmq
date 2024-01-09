package com.example.demospringrabbitmq.application;

import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ReservationUpdater reservationUpdater;

    public ReservationService(ReservationUpdater reservationUpdater) {
        this.reservationUpdater = reservationUpdater;
    }

    public void cancel(long reservationId) {
        reservationUpdater.cancelReservation(reservationId);
    }
}
