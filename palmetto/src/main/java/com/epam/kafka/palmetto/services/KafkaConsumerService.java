package com.epam.kafka.palmetto.services;

import com.epam.kafka.palmetto.dtos.OrderDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

  @KafkaListener(topics = "orders", containerFactory = "orderKafkaListenerContainerFactory")
  public void orderListener(OrderDTO orderDTO) {
    // process greeting message
    System.out.println(
        "RECEIVED ORDER ON PALMETTO RESTAURANT: "
            + orderDTO.getId()
            + " | "
            + orderDTO.getFlavor());
  }
}
