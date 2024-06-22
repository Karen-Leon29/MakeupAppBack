package com.dorysoft.mackeupApp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String nameProduct;
    @Column(length = 500, nullable = false)
    private String description;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private Integer amount;
    @Column(length = 50, nullable = false, unique = true)
    private String codeProduct;
    @Column(length = 100)
    private String photoProduct;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
