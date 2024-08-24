package com.example.customershopbackend.entities.customer_type.feture;

import com.example.customershopbackend.entities.customer_type.feture.dto.CustomerTypeRequest;
import com.example.customershopbackend.entities.customer_type.feture.dto.CustomerTypeResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer_type")
@RequiredArgsConstructor
public class CustomerTypeController {

    private final CustomerTypeService customerTypeService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CustomerTypeResponse createCustomerType(@Valid @RequestBody CustomerTypeRequest customerTypeRequest){
        return customerTypeService.createCustomerType(customerTypeRequest);
    }

    @PutMapping("/{uuid}")
    public CustomerTypeResponse updateByUuid(@PathVariable String uuid, @Valid @RequestBody CustomerTypeRequest customerTypeRequest){
        return customerTypeService.updateByUuid(uuid, customerTypeRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{type}")
    public void deleteByType(@PathVariable String type){
        customerTypeService.deleteByType(type);
    }

    @GetMapping("/{type}")
    public CustomerTypeResponse findByType(@PathVariable String type){
        return customerTypeService.findByType(type);
    }

    @GetMapping("/uuid/{uuid}")
    public CustomerTypeResponse findByUuid(@PathVariable String uuid){
        return customerTypeService.findByUuid(uuid);
    }

    @GetMapping
    public List<CustomerTypeResponse> findAll(){
        return customerTypeService.findAll();
    }

}
