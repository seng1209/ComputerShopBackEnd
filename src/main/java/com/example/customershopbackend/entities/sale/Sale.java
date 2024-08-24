package com.example.customershopbackend.entities.sale;

import com.example.customershopbackend.entities.customer.Customer;
import com.example.customershopbackend.entities.sales_products.SalesProducts;
import com.example.customershopbackend.entities.staff.Staff;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "sales")
@Setter
@Getter
@NoArgsConstructor
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String uuid;
    private LocalDateTime saleDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    private BigDecimal totalAmount;
    private Boolean isDeleted;

    @OneToMany(mappedBy = "sale")
    private Set<SalesProducts> salesProducts;

}
