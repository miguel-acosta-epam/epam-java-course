package com.epam.kafka.client.services;

import com.epam.kafka.client.dtos.OrderDTO;
import com.epam.kafka.client.enums.OrderStatus;
import com.epam.kafka.client.mappers.OrderMapper;
import com.epam.kafka.client.models.OrderEntity;
import com.epam.kafka.client.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
  private final Logger log = LoggerFactory.getLogger(OrderService.class.getSimpleName());

  @Autowired private OrderRepository orderRepository;
  @Autowired private ClientKafkaService clientKafkaService;
  @Autowired private OrderMapper orderMapper;

  public List<OrderEntity> getAllOrders() {
    return orderRepository.findAll();
  }

  public void placeOrder(OrderDTO orderDTO) {
    log.info("New order received, order=[" + orderDTO.toString() + "]");

    orderDTO.setStatus(OrderStatus.ORDERED);

    var orderEntity = orderMapper.mapOrderDTOToEntity(orderDTO);
    orderRepository.save(orderEntity);

    var updatedOrderDTO = orderMapper.mapOrderEntityToDTO(orderEntity);

    clientKafkaService.sendOrderToTopic(updatedOrderDTO);
  }

  public void updateOrderStatus(OrderDTO orderDTO) throws Exception {
    var optOrder = orderRepository.findById(orderDTO.getId());

    if (optOrder.isEmpty()) {
      log.error("Order not found id: " + orderDTO.getId());
      throw new Exception("Order with id: " + orderDTO.getId() + " not found");
    }

    log.info("Updating order... id:" + orderDTO.getId() + ", with status: " + orderDTO.getStatus());

    var orderEntity = optOrder.get();
    orderEntity.setStatus(orderDTO.getStatus());

    orderRepository.save(orderEntity);
  }
}
