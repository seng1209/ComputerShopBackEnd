package com.example.customershopbackend.entities.customer.feture;

import com.example.customershopbackend.entities.customer.Customer;
import com.example.customershopbackend.entities.customer.feture.dto.CustomerRequest;
import com.example.customershopbackend.entities.customer.feture.dto.CustomerResponse;
import com.example.customershopbackend.entities.customer.feture.dto.UpdateCustomerRequest;
import com.example.customershopbackend.entities.customer.feture.dto.UpdateCustomerType;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer fromCustomerRequest(CustomerRequest customerRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUpdateCustomerRequest(@MappingTarget Customer customer, UpdateCustomerRequest updateCustomerRequest);

    @Mapping(source = "customerType", target = "customerType")
    CustomerResponse toCustomerResponse(Customer customer);

    List<CustomerResponse> toCustomerResponseList(List<Customer> customers);

}
