package com.epam.kafka.client.controllers;

import com.epam.kafka.client.dtos.OrderDTO;
import com.epam.kafka.client.models.OrderEntity;
import com.epam.kafka.client.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {
  @Autowired private OrderService orderService;

  @GetMapping
  public List<OrderEntity> getAllOrders() {
    return orderService.getAllOrders();
  }

  @PostMapping
  public void placeOrder(@RequestBody OrderDTO orderDTO) {
    orderService.placeOrder(orderDTO);
  }
}
