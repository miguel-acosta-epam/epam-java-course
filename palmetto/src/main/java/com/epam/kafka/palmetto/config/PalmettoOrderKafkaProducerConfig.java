package com.epam.kafka.palmetto.config;

import com.epam.kafka.palmetto.dtos.OrderDTO;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PalmettoOrderKafkaProducerConfig {
  @Value(value = "${spring.kafka.bootstrap-servers}")
  private String bootstrapAddress;

  @Bean
  public Map<String, Object> notificationProducerConfigs() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

    return configProps;
  }

  @Bean
  public ProducerFactory<String, OrderDTO> producerFactory() {
    return new DefaultKafkaProducerFactory<>(notificationProducerConfigs());
  }

  @Bean
  public KafkaTemplate<String, OrderDTO> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }
}
