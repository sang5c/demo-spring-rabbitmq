package com.example.demospringrabbitmq.consumer.reservation.listener;

import com.example.demospringrabbitmq.consumer.reservation.application.ClearDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.example.demospringrabbitmq.config.ReservationMqQueueConfig.RESERVATION_CANCELLATION_QUEUE;

@Component
public class ReservationCancellationListener {

    private static final Logger log = LoggerFactory.getLogger(ReservationCancellationListener.class);
    private final ClearDataService clearDataService;

    public ReservationCancellationListener(ClearDataService clearDataService) {
        this.clearDataService = clearDataService;
    }

    @RabbitListener(queues = RESERVATION_CANCELLATION_QUEUE)
    public void consumeReservationCancellation(long reservationId) {
        log.info("reservation id: " + reservationId);
        clearDataService.clearData();
    }
}
