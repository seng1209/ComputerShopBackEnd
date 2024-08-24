package com.example.customershopbackend.entities.sales_products;

import com.example.customershopbackend.entities.product.Product;
import com.example.customershopbackend.entities.sale.Sale;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "sales_products")
@Setter
@Getter
@NoArgsConstructor
public class SalesProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Positive
    private Integer saleQuantity;
    @Positive
    private BigDecimal saleUnitPrice;
    @PositiveOrZero
    private BigDecimal discount;
    @Positive
    private BigDecimal amount;

}
