package com.dorysoft.mackeupApp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 50, nullable = false)
    private String lastName;
    @Column(length = 100, nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private Long phone;
    @Column(length = 50, nullable = false)
    private  String password;
    @Column(length = 50, nullable = false)
    private String address;
    @Column(length = 50, nullable = false)
    private String rol;
}
