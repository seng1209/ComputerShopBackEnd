package com.example.customershopbackend.entities.supplier;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "suppliers")
@Setter
@Getter
@NoArgsConstructor
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String uuid;
    private String name;
    @Column(length = 24, unique = true)
    private String phone;
    @Column(columnDefinition = "TEXT")
    private String address;
    private Boolean isDeleted;

}
