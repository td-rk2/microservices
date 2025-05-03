package com.Tz.PaymentService;

import com.Tz.Models.PaymentOrder;
import com.Tz.domain.PaymentMethod;

import com.Tz.payload.dto.BookingDTO;
import com.Tz.payload.dto.UserDTO;
import com.Tz.payload.response.PaymentLinkResponse;

public interface PaymentService {

    PaymentLinkResponse createOrder(UserDTO userDTO,
                                    BookingDTO bookingDTO,
                                    PaymentMethod paymentMethod);

    PaymentOrder getPaymentOrderById(Long id) throws Exception;

    PaymentOrder getPaymentOrderByPaymentId(String paymentId);

    PaymentLink createRazorpayPaymentLink(UserDTO userDTO,
                                    Long amount,
                                    Long orderId);
    String createStripePaymentLink(UserDTO userDTO,
                                   Long amount,
                                   Long orderId);

    Boolean proceedPayment(PaymentOrder paymentOrder,
                           String paymentId,
                           String paymentLinkId) throws Exception;

}
