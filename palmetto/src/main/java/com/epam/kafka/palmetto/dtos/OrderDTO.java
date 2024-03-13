package com.epam.kafka.palmetto.dtos;

import com.epam.kafka.palmetto.enums.OrderStatus;

public class OrderDTO {
    private Long id;
    private String flavor;
    private int quantity;
    private OrderStatus status;

    public OrderDTO() {}

    public OrderDTO(Long id, String flavor, int quantity, OrderStatus status) {
        this.id = id;
        this.flavor = flavor;
        this.quantity = quantity;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}

