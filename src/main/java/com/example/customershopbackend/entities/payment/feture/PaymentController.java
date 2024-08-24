package com.example.customershopbackend.entities.payment.feture;

import com.example.customershopbackend.entities.payment.feture.dto.PaymentRequest;
import com.example.customershopbackend.entities.payment.feture.dto.PaymentResponse;
import com.example.customershopbackend.entities.payment.feture.dto.UpdatePaymentRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PaymentResponse createPayment(@Valid @RequestBody PaymentRequest paymentRequest){
        return paymentService.createPayment(paymentRequest);
    }

    @PatchMapping("/{uuid}")
    public PaymentResponse updateByUuid(@PathVariable String uuid, @Valid @RequestBody UpdatePaymentRequest updatePaymentRequest){
        return paymentService.updateByUuid(uuid, updatePaymentRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{uuid}")
    public void updateIsDeletedByUuid(@PathVariable String uuid){
        paymentService.updateIsDeletedByUuid(uuid);
    }

    @GetMapping("/{uuid}")
    public PaymentResponse findByUuid(@PathVariable String uuid){
        return paymentService.findByUuid(uuid);
    }

    @GetMapping
    public List<PaymentResponse> findAll(){
        return paymentService.findAll();
    }

}
