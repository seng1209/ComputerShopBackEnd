package com.example.customershopbackend.entities.payment.feture;

import com.example.customershopbackend.entities.payment.feture.dto.PaymentRequest;
import com.example.customershopbackend.entities.payment.feture.dto.PaymentResponse;
import com.example.customershopbackend.entities.payment.feture.dto.UpdatePaymentRequest;

import java.util.List;

public interface PaymentService {

    PaymentResponse createPayment(PaymentRequest paymentRequest);

    PaymentResponse updateByUuid(String uuid, UpdatePaymentRequest updatePaymentRequest);

    void updateIsDeletedByUuid(String uuid);

    PaymentResponse findByUuid(String uuid);

    List<PaymentResponse> findAll();

}
