package com.example.customershopbackend.entities.sale.feture;

import com.example.customershopbackend.entities.customer.Customer;
import com.example.customershopbackend.entities.customer.feture.CustomerRepository;
import com.example.customershopbackend.entities.sale.Sale;
import com.example.customershopbackend.entities.sale.feture.dto.SaleRequest;
import com.example.customershopbackend.entities.sale.feture.dto.SaleResponse;
import com.example.customershopbackend.entities.sale.feture.dto.UpdateSaleRequest;
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
public class SaleServiceImpl implements SaleService {

    private final SaleMapper saleMapper;
    private final SaleRepository saleRepository;
    private final CustomerRepository customerRepository;
    private final StaffRepository staffRepository;

    @Override
    public SaleResponse createSale(SaleRequest saleRequest) {

        Customer customer = customerRepository.findByPhone(saleRequest.customerPhone()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found!")
        );

        Staff staff = staffRepository.findByUuid(saleRequest.staffUuid()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff not found!")
        );

        Sale sale = saleMapper.fromSaleRequest(saleRequest);
        sale.setUuid(RandomUtil.random6Digits());
        sale.setSaleDate(LocalDateTime.now());
        sale.setStaff(staff);
        sale.setCustomer(customer);
        sale.setIsDeleted(false);

        saleRepository.save(sale);

        return saleMapper.toSaleResponse(sale);
    }

    @Override
    public SaleResponse updateByUuid(String uuid, UpdateSaleRequest updateSaleRequest) {

        if (saleRepository.existsByUuid(uuid)){
            Sale sale = saleRepository.findByUuid(uuid).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale not found!")
            );
            saleMapper.fromUpdateSaleRequest(sale, updateSaleRequest);
            if (updateSaleRequest.customerPhone() != null){
                Customer customer = customerRepository.findByPhone(updateSaleRequest.customerPhone()).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found!")
                );
                sale.setCustomer(customer);
            }
            if (updateSaleRequest.staffUuid() != null){
                Staff staff = staffRepository.findByUuid(uuid).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff not found!")
                );
                sale.setStaff(staff);
            }
            saleRepository.save(sale);
            return saleMapper.toSaleResponse(sale);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale not found!");
    }

    @Transactional
    @Override
    public void updateIsDeletedByUuid(String uuid) {
        saleRepository.updateByIsDeleted(uuid);
    }

    @Override
    public SaleResponse findByUuid(String uuid) {
        Sale sale = saleRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale not found!")
        );
        return saleMapper.toSaleResponse(sale);
    }

    @Override
    public List<SaleResponse> findAll() {
        List<Sale> sales = saleRepository.findAllByIsDeletedIsFalse();
        return saleMapper.toSaleResponseList(sales);
    }
}
