package com.epam.kafka.client.services;

import com.epam.kafka.client.models.OrderEntity;
import com.epam.kafka.client.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
  @Autowired private OrderRepository orderRepository;

  public List<OrderEntity> getAllOrders() {
    return orderRepository.findAll();
  }
}
