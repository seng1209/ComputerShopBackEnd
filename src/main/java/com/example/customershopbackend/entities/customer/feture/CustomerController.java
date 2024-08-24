package com.example.customershopbackend.entities.customer.feture;

import com.example.customershopbackend.entities.customer.feture.dto.CustomerRequest;
import com.example.customershopbackend.entities.customer.feture.dto.CustomerResponse;
import com.example.customershopbackend.entities.customer.feture.dto.UpdateCustomerRequest;
import com.example.customershopbackend.entities.customer.feture.dto.UpdateCustomerType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CustomerResponse createCustomer(@Valid @RequestBody CustomerRequest customerRequest){
        return customerService.createCustomer(customerRequest);
    }

    @PatchMapping("/{phone}")
    public CustomerResponse updateCustomerByPhone(@PathVariable String phone, @Valid @RequestBody UpdateCustomerRequest updateCustomerRequest){
        return customerService.updateCustomerByPhone(phone, updateCustomerRequest);
    }

    @PutMapping("/{phone}")
    public void updateIsDeletedByPhone(@PathVariable String phone){
        customerService.updateIsDeletedByPhone(phone);
    }

    @PutMapping("/type/{phone}")
    public CustomerResponse updateCustomerType(@PathVariable String phone, @Valid @RequestBody UpdateCustomerType updateCustomerType){
        return customerService.updateCustomerType(phone, updateCustomerType);
    }

    @GetMapping("/phone/{phone}")
    public CustomerResponse findByPhone(@PathVariable String phone){
        return customerService.findByPhone(phone);
    }

    @GetMapping("/email/{email}")
    public CustomerResponse findByEmail(@PathVariable String email){
        return customerService.findByEmail(email);
    }

    @GetMapping
    public List<CustomerResponse> findAll(){
        return customerService.findAll();
    }

}
