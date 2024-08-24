package com.example.customershopbackend.entities.customer_type.feture;

import com.example.customershopbackend.entities.customer_type.feture.dto.CustomerTypeRequest;
import com.example.customershopbackend.entities.customer_type.feture.dto.CustomerTypeResponse;

import java.util.List;

public interface CustomerTypeService {

    CustomerTypeResponse createCustomerType(CustomerTypeRequest customerTypeRequest);

    CustomerTypeResponse updateByUuid(String uuid, CustomerTypeRequest customerTypeRequest);

    void deleteByType(String type);

    CustomerTypeResponse findByType(String type);

    CustomerTypeResponse findByUuid(String uuid);

    List<CustomerTypeResponse> findAll();

}
