package com.dorysoft.mackeupApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private Long userId;
    private Long productId;
    private int quantity;
    private String status;
    private Double totalPrice;
    private Boolean isActive;
}
