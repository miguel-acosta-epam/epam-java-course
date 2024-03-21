package com.epam.kafka.client.services;

import com.epam.kafka.client.dtos.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ClientKafkaConsumerService {
  private final Logger log =
      LoggerFactory.getLogger(ClientKafkaConsumerService.class.getSimpleName());

  @Autowired OrderService orderService;

  @KafkaListener(topics = "notifications", containerFactory = "orderKafkaListenerContainerFactory")
  public void notificationsListener1(OrderDTO orderDTO) throws Exception {
    handleKafkaListener(orderDTO);
  }

  @KafkaListener(topics = "notifications", containerFactory = "orderKafkaListenerContainerFactory")
  public void notificationsListener2(OrderDTO orderDTO) throws Exception {
    handleKafkaListener(orderDTO);
  }

  @KafkaListener(topics = "notifications", containerFactory = "orderKafkaListenerContainerFactory")
  public void notificationsListener3(OrderDTO orderDTO) throws Exception {
    handleKafkaListener(orderDTO);
  }

  private void handleKafkaListener(OrderDTO orderDTO) throws Exception {
    log.info(
        "Receiving order on client... "
            + orderDTO.getId()
            + " | "
            + orderDTO.getFlavor()
            + ", with status: "
            + orderDTO.getStatus());

    orderService.updateOrderStatus(orderDTO);
  }
}
