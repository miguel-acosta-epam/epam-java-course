package com.epam.kafka.palmetto.config;

import com.epam.kafka.palmetto.dtos.OrderDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class OrderDeserializer implements Deserializer<OrderDTO> {
  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {}

  @Override
  public OrderDTO deserialize(String s, byte[] data) {
    try {
      if (data == null) {
        System.out.println("Null received at deserializing");
        return null;
      }
      System.out.println("Deserializing...");
      return objectMapper.readValue(new String(data, StandardCharsets.UTF_8), OrderDTO.class);
    } catch (Exception e) {
      throw new SerializationException("Error when deserializing byte[] to MessageDto");
    }
  }

  @Override
  public void close() {}
}
