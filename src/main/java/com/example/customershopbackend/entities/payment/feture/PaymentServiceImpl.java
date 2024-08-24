package com.example.customershopbackend.entities.payment.feture;

import com.example.customershopbackend.entities.customer.Customer;
import com.example.customershopbackend.entities.customer.feture.CustomerRepository;
import com.example.customershopbackend.entities.payment.Payment;
import com.example.customershopbackend.entities.payment.feture.dto.PaymentRequest;
import com.example.customershopbackend.entities.payment.feture.dto.PaymentResponse;
import com.example.customershopbackend.entities.payment.feture.dto.UpdatePaymentRequest;
import com.example.customershopbackend.entities.staff.Staff;
import com.example.customershopbackend.entities.staff.feture.StaffRepository;
import com.example.customershopbackend.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final CustomerRepository customerRepository;
    private final StaffRepository staffRepository;

    @Override
    public PaymentResponse createPayment(PaymentRequest paymentRequest) {

        Customer customer = customerRepository.findByPhone(paymentRequest.customerPhone()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found!")
        );

        Staff staff = staffRepository.findByUuid(paymentRequest.staffUuid()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff not found!")
        );

        Payment payment = paymentMapper.fromPaymentRequest(paymentRequest);
        payment.setUuid(RandomUtil.random6Digits());
        payment.setCustomer(customer);
        payment.setStaff(staff);
        payment.setPayDate(LocalDateTime.now());
        payment.setIsDeleted(false);

        paymentRepository.save(payment);

        return paymentMapper.toPaymentResponse(payment);
    }

    @Override
    public PaymentResponse updateByUuid(String uuid, UpdatePaymentRequest updatePaymentRequest) {

        if (paymentRepository.existsByUuid(uuid)){
            Payment payment = paymentRepository.findByUuid(uuid).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found!")
            );
            paymentMapper.fromUpdatePaymentRequest(payment, updatePaymentRequest);

            Customer customer = customerRepository.findByPhone(updatePaymentRequest.customerPhone()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found!")
            );

            Staff staff = staffRepository.findByUuid(updatePaymentRequest.staffUuid()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff not found!")
            );

            payment.setCustomer(customer);
            payment.setStaff(staff);
            paymentRepository.save(payment);
            return  paymentMapper.toPaymentResponse(payment);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found!");
    }

    @Transactional
    @Override
    public void updateIsDeletedByUuid(String uuid) {
        paymentRepository.updateByIsDeleted(uuid);
    }

    @Override
    public PaymentResponse findByUuid(String uuid) {
        Payment payment = paymentRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found!")
        );
        return paymentMapper.toPaymentResponse(payment);
    }

    @Override
    public List<PaymentResponse> findAll() {
        List<Payment> payments = paymentRepository.findAllByIsDeletedIsFalse();
        return paymentMapper.toPaymentResponseList(payments);
    }
}
