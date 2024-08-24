package com.example.customershopbackend.entities.invoices_products;

import com.example.customershopbackend.entities.invoice.Invoice;
import com.example.customershopbackend.entities.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "invoices_products")
@Setter
@Getter
@NoArgsConstructor
public class InvoicesProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Positive
    private Integer saleQuantity;
    @Positive
    private BigDecimal saleUnitPrice;
    @Positive
    private BigDecimal amount;

}
