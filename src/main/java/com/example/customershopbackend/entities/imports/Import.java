package com.example.customershopbackend.entities.imports;

import com.example.customershopbackend.entities.imports_products.ImportsProducts;
import com.example.customershopbackend.entities.staff.Staff;
import com.example.customershopbackend.entities.supplier.Supplier;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "imports")
@Setter
@Getter
@NoArgsConstructor
public class Import {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String uuid;
    private LocalDateTime importDate;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;
    @Positive
    private BigDecimal totalAmount;
    private Boolean isDeleted;
    @OneToMany(mappedBy = "imports")
    private Set<ImportsProducts> importsProducts;

}
