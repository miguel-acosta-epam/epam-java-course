package com.epam.kafka.palmetto.services;

import com.epam.kafka.palmetto.dtos.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PalmettoKafkaConsumerService {
  private final Logger log = LoggerFactory.getLogger(PalmettoKafkaConsumerService.class.getSimpleName());

  @Autowired PalmettoService palmettoService;

  @KafkaListener(topics = "orders", containerFactory = "orderKafkaListenerContainerFactory")
  public void orderListener(OrderDTO orderDTO) throws InterruptedException {
    log.info("Receiving order on palmetto... " + orderDTO.getId() + " | " + orderDTO.getFlavor());

    palmettoService.handleOrder(orderDTO);
  }
}
