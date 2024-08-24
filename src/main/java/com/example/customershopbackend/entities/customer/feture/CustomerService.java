package com.example.customershopbackend.entities.customer.feture;

import com.example.customershopbackend.entities.customer.feture.dto.CustomerRequest;
import com.example.customershopbackend.entities.customer.feture.dto.CustomerResponse;
import com.example.customershopbackend.entities.customer.feture.dto.UpdateCustomerRequest;
import com.example.customershopbackend.entities.customer.feture.dto.UpdateCustomerType;

import java.util.List;

public interface CustomerService {

    CustomerResponse createCustomer(CustomerRequest customerRequest);

    CustomerResponse updateCustomerByPhone(String phone, UpdateCustomerRequest updateCustomerRequest);

    void updateIsDeletedByPhone(String phone);

    CustomerResponse updateCustomerType(String phone, UpdateCustomerType updateCustomerType);

    void updateIsDeletedByEmail(String email);

    void updateIsDeletedByUuid(String uuid);

    CustomerResponse findByPhone(String phone);

    CustomerResponse findByEmail(String email);

    CustomerResponse findByUuid(String uuid);

    List<CustomerResponse> findAll();

}
