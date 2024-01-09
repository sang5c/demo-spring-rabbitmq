package com.example.demospringrabbitmq.reservation.infrastructure;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import static com.example.demospringrabbitmq.config.ReservationMqQueueConfig.RESERVATION_CANCELLATION_ROUTING_KEY;
import static com.example.demospringrabbitmq.config.ReservationMqQueueConfig.RESERVATION_EVENTS_EXCHANGE;

@Component
public class ReservationCancellationPublisher {

    private final RabbitTemplate rabbitTemplate;

    public ReservationCancellationPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishCancellationEvent(long reservationId) {
        rabbitTemplate.convertAndSend(RESERVATION_EVENTS_EXCHANGE, RESERVATION_CANCELLATION_ROUTING_KEY, reservationId);
    }
}
