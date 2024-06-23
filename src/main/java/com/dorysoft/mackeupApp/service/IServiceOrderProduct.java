package com.dorysoft.mackeupApp.service;

import com.dorysoft.mackeupApp.domain.OrderProduct;

import java.util.List;

public interface IServiceOrderProduct {
    List<OrderProduct> getOrderProducts();
    OrderProduct getOrderProductById(Long id);
    OrderProduct saveOrderProduct(OrderProduct orderProduct);
    OrderProduct updateOrderProduct(OrderProduct orderProduct);
    void deleteOrderProduct(Long id);
}
