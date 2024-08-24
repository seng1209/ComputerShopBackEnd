package com.example.customershopbackend.entities.staff;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "staffs")
@Setter
@Getter
@NoArgsConstructor
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String uuid;
    @Column(columnDefinition = "TEXT")
    private String image;
    @Column(nullable = false, length = 60)
    private String name;
    @Column(length = 6)
    private String gender;
    private LocalDate birthDate;
    @Column(length = 16, unique = true, nullable = false)
    private String phone;
    @Column(unique = true)
    private String email;
    @Column(columnDefinition = "TEXT")
    private String address;
    @Column(length = 120)
    private String position;
    @PositiveOrZero
    private BigDecimal salary;
    private LocalDate hiredDate;
    private Boolean isDeleted;

}
