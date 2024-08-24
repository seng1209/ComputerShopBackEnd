package com.example.customershopbackend.entities.customer_type.feture;

import com.example.customershopbackend.entities.customer_type.CustomerType;
import com.example.customershopbackend.entities.customer_type.feture.dto.CustomerTypeRequest;
import com.example.customershopbackend.entities.customer_type.feture.dto.CustomerTypeResponse;
import com.example.customershopbackend.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerTypeServiceImpl implements CustomerTypeService{

    private final CustomerTypeMapper customerTypeMapper;
    private final CustomerTypeRepository customerTypeRepository;

    @Override
    public CustomerTypeResponse createCustomerType(CustomerTypeRequest customerTypeRequest) {

        if (customerTypeRepository.existsByType(customerTypeRequest.type())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Type already exists");
        }

        CustomerType customerType = customerTypeMapper.fromCustomerTypeRequest(customerTypeRequest);
        customerType.setUuid(RandomUtil.random6Digits());
        customerTypeRepository.save(customerType);

        return customerTypeMapper.toCustomerTypeResponse(customerType);
    }

    @Override
    public CustomerTypeResponse updateByUuid(String uuid, CustomerTypeRequest customerTypeRequest) {
        if (customerTypeRepository.existsByUuid(uuid)){
            CustomerType customerType = customerTypeRepository.findByUuid(uuid).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Type not found!")
            );
            customerTypeMapper.fromUpdateCustomerTypeRequest(customerType, customerTypeRequest);
            customerTypeRepository.save(customerType);
            return customerTypeMapper.toCustomerTypeResponse(customerType);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Type not found!");
    }

    @Override
    public void deleteByType(String type) {
        CustomerType customerType = customerTypeRepository.findByType(type).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Type not found!")
        );
        customerTypeRepository.delete(customerType);
    }

    @Override
    public CustomerTypeResponse findByType(String type) {
        CustomerType customerType = customerTypeRepository.findByType(type).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Type not found!")
        );
        return customerTypeMapper.toCustomerTypeResponse(customerType);
    }

    @Override
    public CustomerTypeResponse findByUuid(String uuid) {
        CustomerType customerType = customerTypeRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Type not found!")
        );
        return customerTypeMapper.toCustomerTypeResponse(customerType);
    }

    @Override
    public List<CustomerTypeResponse> findAll() {
        List<CustomerType> customerTypes = customerTypeRepository.findAll();
        return customerTypeMapper.toCustomerTypeResponseList(customerTypes);
    }
}
