package com.epam.kafka.courier.services;

import com.epam.kafka.courier.dtos.OrderDTO;
import com.epam.kafka.courier.enums.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CourierService {
  private final Logger log = LoggerFactory.getLogger(CourierService.class.getSimpleName());

  @Autowired CourierKafkaService courierKafkaService;

  void deliveryOrder(OrderDTO order) throws InterruptedException {
    log.info("Delivering order... " + order.getId() + " | flavor: " + order.getFlavor());

    // 15 minutes to delivery the pizza
    // TimeUnit.MINUTES.sleep(15);

    TimeUnit.SECONDS.sleep(20);

    log.info("Order delivered!");
    order.setStatus(OrderStatus.DELIVERED);

    courierKafkaService.sendOrderToNotificationTopic(order);
  }
}
