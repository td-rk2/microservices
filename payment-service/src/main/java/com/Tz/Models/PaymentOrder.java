package com.Tz.Models;

import com.Tz.domain.PaymentMethod;
import com.Tz.domain.PaymentOrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class PaymentOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private PaymentOrderStatus status = PaymentOrderStatus.PENDING;

    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    private String paymentLinkId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long bookingId;

    @Column(nullable = false)
    private Long salonId;


}
