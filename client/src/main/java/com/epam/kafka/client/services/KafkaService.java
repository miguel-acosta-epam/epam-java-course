package com.epam.kafka.client.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaService {

  @Value("${tpd.order-topic}")
  private String orderTopic;

  @Value("${tpd.notification-topic}")
  private String notificationTopic;

  private static final Logger log = LoggerFactory.getLogger(KafkaService.class.getSimpleName());
  @Autowired private KafkaTemplate<String, String> kafkaTemplate;

  public void sendMessage(String message) {
    CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(orderTopic, message);
    future.whenComplete(
        (result, ex) -> {
          if (ex != null) {
            log.error("Unable to send message=[" + message + "] due to : " + ex.getMessage());
            return;
          }

          log.info(
              "Sent message=["
                  + message
                  + "] with offset=["
                  + result.getRecordMetadata().offset()
                  + "]");
        });
  }
}
