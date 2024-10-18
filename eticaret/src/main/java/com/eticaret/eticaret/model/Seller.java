package com.eticaret.eticaret.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sellers")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_id")
    private int id;

    @Column(name = "seller_email",nullable = false)
    private String email;

    @Column(name = "seller_name",nullable = false)
    private String name;

    @Column(name = "seller_password",nullable = false)
    private String password;

    @Column(name = "seller_phone")
    private String phone;

    @Column(name = "seller_address")
    private String address;
}
