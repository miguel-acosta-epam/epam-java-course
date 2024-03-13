package com.epam.kafka.client.services;

import com.epam.kafka.client.dtos.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ClientKafkaService {

  @Value("${tpd.order-topic}")
  private String orderTopic;

  @Value("${tpd.notification-topic}")
  private String notificationTopic;

  private static final Logger log = LoggerFactory.getLogger(ClientKafkaService.class.getSimpleName());
  @Autowired private KafkaTemplate<String, OrderDTO> kafkaTemplate;

  public void sendOrderToTopic(OrderDTO order) {
    CompletableFuture<SendResult<String, OrderDTO>> future =
        kafkaTemplate.send(orderTopic, order);
    future.whenComplete(
        (result, ex) -> {
          if (ex != null) {
            log.error("Unable to send message=[" + order.toString() + "] due to : " + ex.getMessage());
            return;
          }

          log.info(
              "Sent message=["
                  + order.toString()
                  + "] with offset=["
                  + result.getRecordMetadata().offset()
                  + "]");
        });
  }
}
