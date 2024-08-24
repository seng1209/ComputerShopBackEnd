package com.example.customershopbackend.entities.product;

import com.example.customershopbackend.entities.category.Category;
import com.example.customershopbackend.entities.imports_products.ImportsProducts;
import com.example.customershopbackend.entities.invoices_products.InvoicesProducts;
import com.example.customershopbackend.entities.sales_products.SalesProducts;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "products")
@Setter
@Getter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String uuid;
    private String image;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    @PositiveOrZero
    private Integer stockQuantity;
    @Positive
    private BigDecimal unitPrice;
    @Positive
    private BigDecimal saleUnitPrice;
    @PositiveOrZero
    private BigDecimal discount;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private Boolean isDeleted;

    @OneToMany(mappedBy = "product")
    private Set<ImportsProducts> importsProducts;

    @OneToMany(mappedBy = "product")
    private Set<InvoicesProducts> invoicesProducts;

    @OneToMany(mappedBy = "product")
    private Set<SalesProducts> salesProducts;

}
