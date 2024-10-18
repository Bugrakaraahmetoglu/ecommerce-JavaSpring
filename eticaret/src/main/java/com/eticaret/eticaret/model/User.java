package com.eticaret.eticaret.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "user_email",nullable = false)
    private String email;

    @Column(name = "user_name",nullable = false)
    private String name;

    @Column(name = "user_password",nullable = false)
    private String password;

    @Column(name = "user_phone")
    private String phone;

    @Column(name = "user_address")
    private String address;
}
