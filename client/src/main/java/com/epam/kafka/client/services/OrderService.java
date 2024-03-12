package com.epam.kafka.client.services;

import com.epam.kafka.client.dtos.OrderDTO;
import com.epam.kafka.client.enums.OrderStatus;
import com.epam.kafka.client.mappers.OrderMapper;
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
  @Autowired private OrderMapper orderMapper;

  public List<OrderEntity> getAllOrders() {
    return orderRepository.findAll();
  }

  public void placeOrder(OrderDTO orderDTO) {
    orderDTO.setStatus(OrderStatus.ORDERED);

    var orderEntity = orderMapper.mapOrderDTOToEntity(orderDTO);
    orderRepository.save(orderEntity);

    kafkaService.sendOrderToTopic(orderEntity);
  }
}
