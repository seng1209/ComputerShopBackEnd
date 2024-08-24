package com.example.customershopbackend.entities.imports_products;

import com.example.customershopbackend.entities.imports.Import;
import com.example.customershopbackend.entities.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "imports_products")
@Setter
@Getter
@NoArgsConstructor
public class ImportsProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    String uuid;

    @ManyToOne
    @JoinColumn(name = "imports_id")
    private Import imports;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer importQuantity;
    @Positive
    private BigDecimal unitPrice;
    @Positive
    private BigDecimal amount;

}
