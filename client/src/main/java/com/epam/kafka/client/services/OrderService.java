package com.epam.kafka.client.services;

import com.epam.kafka.client.enums.OrderStatus;
import com.epam.kafka.client.models.OrderEntity;
import com.epam.kafka.client.repository.OrderRepository;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
  @Autowired private OrderRepository orderRepository;
  @Autowired private KafkaService kafkaService;

  public List<OrderEntity> getAllOrders() {
    return orderRepository.findAll();
  }

  public void placeOrder() {
    var orders = getAllOrders();
    OrderEntity order = null;

    if (orders.isEmpty()) {
      order =
          OrderEntity.builder()
              .id(1L)
              .flavor("Pollo")
              .quantity(1)
              .status(OrderStatus.ORDERED)
              .build();
    } else {
      order = orders.get(0);
    }

    kafkaService.sendOrderToTopic(order);
  }
}
