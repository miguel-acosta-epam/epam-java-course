package com.epam.kafka.palmetto.services;

import com.epam.kafka.palmetto.dtos.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PalmettoKafkaConsumerService {
  private final Logger log =
      LoggerFactory.getLogger(PalmettoKafkaConsumerService.class.getSimpleName());

  @Autowired PalmettoService palmettoService;

  @KafkaListener(topics = "orders", containerFactory = "orderKafkaListenerContainerFactory")
  public void orderListener1(OrderDTO orderDTO) throws InterruptedException {
    handleKafkaListener(orderDTO);
  }

  @KafkaListener(topics = "orders", containerFactory = "orderKafkaListenerContainerFactory")
  public void orderListener2(OrderDTO orderDTO) throws InterruptedException {
    handleKafkaListener(orderDTO);
  }

  @KafkaListener(topics = "orders", containerFactory = "orderKafkaListenerContainerFactory")
  public void orderListener3(OrderDTO orderDTO) throws InterruptedException {
    handleKafkaListener(orderDTO);
  }

  private void handleKafkaListener(OrderDTO orderDTO) throws InterruptedException {
    log.info("Receiving order on palmetto... " + orderDTO.getId() + " | " + orderDTO.getFlavor());

    palmettoService.cookOrder(orderDTO);
  }
}
