package com.TZ.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
public class Salon {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private Long Id;
    @Column(nullable = false)
    private String name;
    @ElementCollection
    private List<String> images;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private Long ownerId;
    @Column(nullable = false)
    private LocalTime openTime;
    @Column(nullable = false)
    private LocalTime closeTime;
}
