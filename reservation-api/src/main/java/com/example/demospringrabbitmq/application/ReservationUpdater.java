package com.example.demospringrabbitmq.application;

import com.example.demospringrabbitmq.reservation.infrastructure.ReservationCancellationPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ReservationUpdater {
    private static final Logger log = LoggerFactory.getLogger(ReservationUpdater.class);

    private final ReservationCancellationPublisher cancellationPublisher;

    public ReservationUpdater(ReservationCancellationPublisher cancellationPublisher) {
        this.cancellationPublisher = cancellationPublisher;
    }

    @Transactional
    public void cancelReservation(long reservationId) {
        log.info("reservation id: {}", reservationId);
        cancellationPublisher.publishCancellationEvent(reservationId);
    }
}
