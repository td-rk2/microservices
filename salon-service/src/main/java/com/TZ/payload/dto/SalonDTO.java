package com.TZ.payload.dto;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class SalonDTO {

    private Long Id;
    
    private String name;

    private List<String> images;
    
    private String address;
    
    private String phone;
    
    private String email;
    
    private String city;
    
    private Long ownerId;
    
    private LocalDateTime openTime;
    
    private LocalDateTime closeTime;
}
