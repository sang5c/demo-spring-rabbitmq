package com.example.demospringrabbitmq.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Reservation {

    @Id @GeneratedValue
    private Long id;

    private String username;
}
