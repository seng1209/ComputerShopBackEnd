package com.example.customershopbackend.entities.invoice;

import com.example.customershopbackend.entities.customer.Customer;
import com.example.customershopbackend.entities.invoices_products.InvoicesProducts;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "invoices")
@Setter
@Getter
@NoArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String uuid;
    private LocalDateTime invoiceDate;
    @Positive
    private BigDecimal totalAmount;
    @PositiveOrZero
    private BigDecimal paidAmount;
    @PositiveOrZero
    private BigDecimal oweAmount;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private Boolean isDeleted;

    @OneToMany(mappedBy = "invoice")
    private Set<InvoicesProducts> invoicesProducts;

}
