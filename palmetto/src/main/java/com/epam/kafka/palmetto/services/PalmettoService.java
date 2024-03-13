package com.epam.kafka.palmetto.services;

import com.epam.kafka.palmetto.dtos.OrderDTO;
import com.epam.kafka.palmetto.enums.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PalmettoService {
  private final Logger log = LoggerFactory.getLogger(PalmettoService.class.getSimpleName());

  @Autowired PalmettoKafkaService palmettoKafkaService;

  void handleOrder(OrderDTO order) throws InterruptedException {
    log.info("Cooking order... " + order.getId() + " | flavor: " + order.getFlavor());

    // 2 minutes to cook a pizza
    // TimeUnit.MINUTES.sleep(2);

    TimeUnit.SECONDS.sleep(30);

    log.info("Order ready!");
    order.setStatus(OrderStatus.READY);

    palmettoKafkaService.sendOrderToNotificationTopic(order);
  }
}
