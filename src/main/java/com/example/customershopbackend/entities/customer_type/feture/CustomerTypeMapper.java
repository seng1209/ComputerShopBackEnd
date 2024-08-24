package com.example.customershopbackend.entities.customer_type.feture;

import com.example.customershopbackend.entities.customer_type.CustomerType;
import com.example.customershopbackend.entities.customer_type.feture.dto.CustomerTypeRequest;
import com.example.customershopbackend.entities.customer_type.feture.dto.CustomerTypeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerTypeMapper {

    CustomerType fromCustomerTypeRequest(CustomerTypeRequest customerTypeRequest);

    void fromUpdateCustomerTypeRequest(@MappingTarget CustomerType customerType, CustomerTypeRequest customerTypeRequest);

    CustomerTypeResponse toCustomerTypeResponse(CustomerType customerType);

    List<CustomerTypeResponse> toCustomerTypeResponseList(List<CustomerType> customerTypes);

}
