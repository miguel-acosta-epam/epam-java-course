package com.epam.kafka.courier.services;

import com.epam.kafka.courier.dtos.OrderDTO;
import com.epam.kafka.courier.enums.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CourierKafkaConsumerService {
  private final Logger log =
      LoggerFactory.getLogger(CourierKafkaConsumerService.class.getSimpleName());

  @Autowired CourierService courierService;

  @KafkaListener(topics = "notifications", containerFactory = "orderKafkaListenerContainerFactory")
  public void orderListener1(OrderDTO orderDTO) throws InterruptedException {
    handleKafkaListener(orderDTO);
  }

  @KafkaListener(topics = "notifications", containerFactory = "orderKafkaListenerContainerFactory")
  public void orderListener2(OrderDTO orderDTO) throws InterruptedException {
    handleKafkaListener(orderDTO);
  }

  @KafkaListener(topics = "notifications", containerFactory = "orderKafkaListenerContainerFactory")
  public void orderListener3(OrderDTO orderDTO) throws InterruptedException {
    handleKafkaListener(orderDTO);
  }

  private void handleKafkaListener(OrderDTO orderDTO) throws InterruptedException {
    log.info(
        "Receiving notification on courier app... "
            + orderDTO.getId()
            + " | "
            + orderDTO.getFlavor());

    // TODO: no estoy seguro como manejar este escenario.
    if (orderDTO.getStatus().equals(OrderStatus.DELIVERED)) {
      log.info("Received a delivered pizza.");
      return;
    }

    courierService.deliveryOrder(orderDTO);
  }
}
