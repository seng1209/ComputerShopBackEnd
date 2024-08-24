package com.example.customershopbackend.entities.customer;

import com.example.customershopbackend.entities.customer_type.CustomerType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customers")
@Setter
@Getter
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String uuid;
    private String image;
    @Column(length = 60)
    private String name;
    @Column(length = 16, nullable = false, unique = true)
    private String phone;
    @Column(unique = true)
    private String email;
    @Column(columnDefinition = "TEXT")
    private String address;

    @ManyToOne
    @JoinColumn(name = "customer_type_id")
    private CustomerType customerType;

    private Boolean isDeleted;

}
