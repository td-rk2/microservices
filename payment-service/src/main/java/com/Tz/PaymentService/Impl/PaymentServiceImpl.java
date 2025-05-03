package com.Tz.PaymentService.Impl;

import com.Tz.Models.PaymentOrder;
import com.Tz.PaymentService.PaymentService;
import com.Tz.Repositories.PaymentRepository;
import com.Tz.domain.PaymentMethod;
import com.Tz.domain.PaymentOrderStatus;
import com.Tz.payload.dto.BookingDTO;
import com.Tz.payload.dto.UserDTO;
import com.Tz.payload.response.PaymentLinkResponse;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Value("${stripe.api.key}")
    private String stripeSecretKey;

    @Value("${razorpay.api.key}")
    private String razorpayApiKey;

    @Value("${razorpay.api.secret}")
    private String razorpaySecretKey;

    @Override
    public PaymentLinkResponse createOrder(UserDTO userDTO, BookingDTO bookingDTO, PaymentMethod paymentMethod) {

        Long amount = (long) bookingDTO.getTotalPrice();

        PaymentOrder order = new PaymentOrder();
        order.setAmount(amount);
        order.setPaymentMethod(paymentMethod);
        order.setBookingId(bookingDTO.getId());
        order.setSalonId(bookingDTO.getSalonId());

        PaymentOrder savedOrder = paymentRepository.save(order);

        PaymentLinkResponse paymentLinkResponse = new PaymentLinkResponse();

        if(paymentMethod.equals(PaymentMethod.RAZORPAY)) {
            PaymentLink payment = createRazorpayPaymentLink(userDTO,
                    savedOrder.getAmount(),
                    savedOrder.getId());

            String paymentUrl=payment.get("short_url");
            String paymentUrlId=payment.get("id");

            paymentLinkResponse.setPayment_link_url(paymentUrl);
            paymentLinkResponse.setPayment_link_id(paymentUrlId);

            savedOrder.setPaymentLinkId(paymentUrlId);

            paymentRepository.save(savedOrder);

        }else {
            String paymentUrl = createStripePaymentLink(userDTO,
                    savedOrder.getAmount(),
                    savedOrder.getId());
            paymentLinkResponse.setPayment_link_url(paymentUrl);

        }

        return paymentLinkResponse;
    }


    @Override
    public PaymentOrder getPaymentOrderById(Long id) throws Exception {

        PaymentOrder paymentOrder = paymentRepository.findById(id).orElse(null);
        if (paymentOrder==null) {
            throw new Exception("Payment order not found");
        }

        return paymentOrder;
    }

    @Override
    public PaymentOrder getPaymentOrderByPaymentId(String paymentId) {
        return paymentRepository.findByPaymentLinkId(paymentId);
    }

    @Override
    public PaymentLink createRazorpayPaymentLink(UserDTO userDTO,
                                                 Long Amount,
                                                 Long orderId) throws RazorpayException {

        Long amount = Amount*100;

        RazorPayClient razorpay = new RazorPayClient(razorpayApiKey, razorpaySecretKey);

        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount", amount);
        paymentLinkRequest.put("currency", "INR");

        JSONObject customer = new JSONObject();
        customer.put("name", userDTO.getFullName());
        customer.put("email", userDTO.getEmail());

        PaymentLinkRequest.put("customer", customer);

        JSONObject notify = new JSONObject();
        notify.put("email", true);

        PaymentLinkRequest.put("notify", notify);

        PaymentLinkRequest.put("reminder_enable", true);

        PaymentLinkRequest.put("callback_url", "http://localhost:3000/payment-success/"+orderId);

        PaymentLinkRequest.put("callback_method", "get");

        return razorpay.paymentLink.create(paymentLinkRequest);


    }

    @Override
    public String createStripePaymentLink(UserDTO userDTO, Long amount, Long orderId) {

        Stripe.apiKey = stripeSecretKey;

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:3000/payment-success/"+orderId)
                .setCancelUrl("http://localhost:3000/payment/cancel")
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency("usd")
                                                .setUnitAmount(amount*100)
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName("salon appointment booking").build()
                                                ).build()

                                ).build()
                ).build();

        Session session = Session.create(params);[]


        return session.getUrl();
    }

    @Override
    public Boolean proceedPayment(PaymentOrder paymentOrder, String paymentId, String paymentLinkId) throws Exception {

        if(paymentOrder.getStatus().equals(PaymentOrderStatus.PENDING)) {
            if(paymentOrder.getPaymentMethod().equals(PaymentMethod.RAZORPAY)) {
                RazorPayClient razorpay = new RazorPayClient(razorpayApiKey, razorpaySecretKey);

                Payment payment = razorpay.payment.fetch(paymentId);
                Integer amount = payment.get("status");

                if(status.equals("captured")){
                    // produce kafka event
                    paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
                    paymentOrderRepository.save(paymentOrder);
                    return true;
                }
                return false;
            }
            else {
                paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
                paymentOrderRepository.save(paymentOrder);
                return true;
            }
        }
        return false;
    }
}
