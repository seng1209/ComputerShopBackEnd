package com.example.customershopbackend.entities.payment.feture;

import com.example.customershopbackend.entities.payment.Payment;
import com.example.customershopbackend.entities.payment.feture.dto.PaymentRequest;
import com.example.customershopbackend.entities.payment.feture.dto.PaymentResponse;
import com.example.customershopbackend.entities.payment.feture.dto.UpdatePaymentRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

//    @Mapping(source = "customerPhone", target = "customer.phone")
//    @Mapping(source = "staffUuid", target = "staff.uuid")
    Payment fromPaymentRequest(PaymentRequest paymentRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUpdatePaymentRequest(@MappingTarget Payment payment, UpdatePaymentRequest updatePaymentRequest);

    @Mapping(source = "customer", target = "customer")
    @Mapping(source = "staff", target = "staff")
    PaymentResponse toPaymentResponse(Payment payment);

    List<PaymentResponse> toPaymentResponseList(List<Payment> payments);

}
