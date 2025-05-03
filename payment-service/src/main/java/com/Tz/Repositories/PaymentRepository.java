package com.Tz.Repositories;

import com.Tz.Models.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentOrder, Long> {

    PaymentOrder findByPaymentLinkId(String paymentLinkId);

}
