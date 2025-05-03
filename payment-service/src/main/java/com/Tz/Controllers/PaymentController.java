package com.Tz.Controllers;

import com.Tz.Models.PaymentOrder;
import com.Tz.PaymentService.PaymentService;
import com.Tz.domain.PaymentMethod;
import com.Tz.payload.dto.BookingDTO;
import com.Tz.payload.dto.UserDTO;
import com.Tz.payload.response.PaymentLinkResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(@RequestBody BookingDTO bookingDTO,
                                                                 @RequestParam PaymentMethod paymentMethod)
                                                                 throws StripeException, RazorPayException {

        UserDTO userDTO = new UserDTO();
        userDTO.setFullName("Ashok");
        userDTO.setEmail("Ashok@gmail.com");
        UserDTO.setId(1L);

        PaymentLinkResponse res = paymentService.createOrder(userDTO, bookingDTO, paymentMethod);
        return ResponseEntity.ok(res);
    }


    @GetMapping("/{paymentOrderId}")
    public ResponseEntity<PaymentOrder> getPaymentOrderById(@PathVariable Long paymentOrderId)
            throws Exception {

        PaymentOrder res = paymentService.getPaymentOrderById(paymentOrderId);
        return ResponseEntity.ok(res);
    }


    @GetMapping("/proceed")
    public ResponseEntity<Boolean> proceedPayment(@RequestParam String paymentId,
                                                       @RequestParam String PaymentLinkId)
            throws Exception {

        PaymentOrder paymentOrder = paymentService.getPaymentOrderByPaymentId(paymentLinkId);

        Boolean res = paymentService.proceedPayment(paymentOrder,
                paymentId,
                paymentLinkId);
        return ResponseEntity.ok(res);
    }


}
