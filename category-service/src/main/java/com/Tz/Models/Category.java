package com.Tz.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Category {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(nullable = false)
    private String Name;

    private String Image;

    @Column(nullable = false)
    private Long salonId;
}
