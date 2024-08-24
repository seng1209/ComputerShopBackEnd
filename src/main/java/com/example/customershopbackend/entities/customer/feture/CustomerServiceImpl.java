package com.example.customershopbackend.entities.customer.feture;

import com.example.customershopbackend.entities.customer.Customer;
import com.example.customershopbackend.entities.customer.feture.dto.CustomerRequest;
import com.example.customershopbackend.entities.customer.feture.dto.CustomerResponse;
import com.example.customershopbackend.entities.customer.feture.dto.UpdateCustomerRequest;
import com.example.customershopbackend.entities.customer.feture.dto.UpdateCustomerType;
import com.example.customershopbackend.entities.customer_type.CustomerType;
import com.example.customershopbackend.entities.customer_type.feture.CustomerTypeRepository;
import com.example.customershopbackend.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CustomerTypeRepository customerTypeRepository;

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {

        if (customerRepository.existsByPhone(customerRequest.phone())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already used!");
        }

        if (customerRepository.existsByEmail(customerRequest.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already used!");
        }

        CustomerType typeNormal = customerTypeRepository.findByNormal();

        Customer customer = customerMapper.fromCustomerRequest(customerRequest);
        customer.setUuid(RandomUtil.random6Digits());
        customer.setCustomerType(typeNormal);
        customer.setIsDeleted(false);
        customerRepository.save(customer);

        return customerMapper.toCustomerResponse(customer);
    }

    @Override
    public CustomerResponse updateCustomerByPhone(String phone, UpdateCustomerRequest updateCustomerRequest) {
        if (customerRepository.existsByPhone(phone)){
            Customer customer = customerRepository.findByPhone(phone).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Phone number not found!")
            );
            customerMapper.fromUpdateCustomerRequest(customer, updateCustomerRequest);
            customerRepository.save(customer);
            return customerMapper.toCustomerResponse(customer);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Phone number not found!");
    }

    @Transactional
    @Override
    public void updateIsDeletedByPhone(String phone) {
        customerRepository.updateByPhone(phone);
    }

    @Override
    public CustomerResponse updateCustomerType(String phone, UpdateCustomerType updateCustomerType) {
        if (customerRepository.existsByPhone(phone)){
            Customer customer = customerRepository.findByPhone(phone).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Phone number not found!")
            );
            CustomerType customerType = customerTypeRepository.findByType(updateCustomerType.type()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Type not found!")
            );
//            log.info("Customer Type : {}", customerType);
//            log.info("Type : {}", updateCustomerType.type());
            customer.setCustomerType(customerType);
            customerRepository.save(customer);
            return customerMapper.toCustomerResponse(customer);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Phone number not found!");
    }

    @Transactional
    @Override
    public void updateIsDeletedByEmail(String email) {
        customerRepository.updateByEmail(email);
    }

    @Transactional
    @Override
    public void updateIsDeletedByUuid(String uuid) {
        customerRepository.updateByUuid(uuid);
    }

    @Override
    public CustomerResponse findByPhone(String phone) {
        Customer customer = customerRepository.findByPhone(phone).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Phone number not found!")
        );
        return customerMapper.toCustomerResponse(customer);
    }

    @Override
    public CustomerResponse findByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Email not found!")
        );
        return customerMapper.toCustomerResponse(customer);
    }

    @Override
    public CustomerResponse findByUuid(String uuid) {
        Customer customer = customerRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UUID not found!")
        );
        return customerMapper.toCustomerResponse(customer);
    }

    @Override
    public List<CustomerResponse> findAll() {
        List<Customer> customers = customerRepository.findAllByIsDeletedIsFalse();
        return customerMapper.toCustomerResponseList(customers);
    }
}
