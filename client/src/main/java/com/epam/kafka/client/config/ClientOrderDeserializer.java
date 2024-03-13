package com.epam.kafka.client.config;

import com.epam.kafka.client.dtos.OrderDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ClientOrderDeserializer implements Deserializer<OrderDTO> {
  private final Logger log = LoggerFactory.getLogger(ClientOrderDeserializer.class.getSimpleName());
  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {}

  @Override
  public OrderDTO deserialize(String s, byte[] data) {
    try {
      if (data == null) {
        log.error("Null received at deserializing");
        return null;
      }
      log.info("Deserializing...");
      return objectMapper.readValue(new String(data, StandardCharsets.UTF_8), OrderDTO.class);
    } catch (Exception e) {
      log.error("Error deserializing", e);
      throw new SerializationException("Error when deserializing byte[] to MessageDto");
    }
  }

  @Override
  public void close() {}
}
