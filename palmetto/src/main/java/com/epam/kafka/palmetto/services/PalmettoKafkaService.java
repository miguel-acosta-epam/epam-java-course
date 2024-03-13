package com.epam.kafka.palmetto.services;

import com.epam.kafka.palmetto.dtos.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class PalmettoKafkaService {
  @Value("${tpd.notification-topic}")
  private String notificationTopic;

  private static final Logger log = LoggerFactory.getLogger(PalmettoKafkaService.class.getSimpleName());

  @Autowired private KafkaTemplate<String, OrderDTO> kafkaTemplate;

  public void sendOrderToNotificationTopic(OrderDTO order) {
    sendOrderToTopic(order, notificationTopic);
  }

  public void sendOrderToTopic(OrderDTO order, String topic) {
    CompletableFuture<SendResult<String, OrderDTO>> future = kafkaTemplate.send(topic, order);
    future.whenComplete(
        (result, ex) -> {
          if (ex != null) {
            log.error(
                "Unable to send message=[" + order.toString() + "] due to : " + ex.getMessage());
            return;
          }

          log.info(
              "Sent message=["
                  + order.toString()
                  + "] with offset=["
                  + result.getRecordMetadata().offset()
                  + "] to topic=["
                  + topic
                  + "]");
        });
  }
}
