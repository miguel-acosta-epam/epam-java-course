package com.epam.kafka.client.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
  private static final Logger log = LoggerFactory.getLogger(KafkaService.class.getSimpleName());
  @Autowired private KafkaTemplate<String, String> kafkaTemplate;

  public void sendMessage(String msg) {
    kafkaTemplate.send("", "hello-world");
    log.info("Message sent: " + msg);
  }
}
