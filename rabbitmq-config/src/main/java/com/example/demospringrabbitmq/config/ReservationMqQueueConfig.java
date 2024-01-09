package com.example.demospringrabbitmq.config;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReservationMqQueueConfig {

    // Exchange, Queue, Routing Key 이름 정의
    public static final String RESERVATION_EVENTS_EXCHANGE = "reservation.events.exchange";
    public static final String RESERVATION_CANCELLATION_QUEUE = "reservation.cancellation.queue";
    public static final String RESERVATION_CANCELLATION_ROUTING_KEY = "reservation.cancellation";

    // Dead Letter Exchange(DLX) 및 Queue(DLQ) 설정
    private static final String RESERVATION_EVENTS_DLX = "reservation.events.dlx";
    private static final String RESERVATION_CANCELLATION_DLQ = "reservation.cancellation.dlq";
    private static final String RESERVATION_CANCELLATION_FAILED_ROUTING_KEY = "reservation.cancellation.failed";

    // 원본 Exchange 정의
    @Bean
    public DirectExchange reservationEventsExchange() {
        return new DirectExchange(RESERVATION_EVENTS_EXCHANGE);
    }

    // 원본 큐 정의
    @Bean
    public Queue reservationCancellationQueue() {
        return QueueBuilder.durable(RESERVATION_CANCELLATION_QUEUE)
                .deadLetterExchange(RESERVATION_EVENTS_DLX)
                .deadLetterRoutingKey(RESERVATION_CANCELLATION_FAILED_ROUTING_KEY)
                .build();
    }

    // 원본 Queue와 Exchange 바인딩
    @Bean
    public Binding reservationCancellationBinding(Queue reservationCancellationQueue, DirectExchange reservationEventsExchange) {
        return BindingBuilder.bind(reservationCancellationQueue)
                .to(reservationEventsExchange)
                .with(RESERVATION_CANCELLATION_ROUTING_KEY);
    }

    // Dead Letter Exchange(DLX) 정의
    @Bean
    public DirectExchange reservationEventsDLX() {
        return new DirectExchange(RESERVATION_EVENTS_DLX);
    }

    // Dead Letter Queue(DLQ) 정의
    @Bean
    public Queue reservationCancellationDLQ() {
        return new Queue(RESERVATION_CANCELLATION_DLQ);
    }

    // Dead Letter Queue와 Exchange 바인딩
    @Bean
    public Binding reservationCancellationDLQBinding(Queue reservationCancellationDLQ, DirectExchange reservationEventsDLX) {
        return BindingBuilder.bind(reservationCancellationDLQ)
                .to(reservationEventsDLX)
                .with(RESERVATION_CANCELLATION_FAILED_ROUTING_KEY);
    }
}
