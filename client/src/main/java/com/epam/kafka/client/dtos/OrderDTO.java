package com.epam.kafka.client.dtos;

import com.epam.kafka.client.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDTO {
  private String flavor;
  private int quantity;
  private OrderStatus status;

  @Override
  public String toString() {
    return "OrderDTO{"
        + "flavor='"
        + flavor
        + '\''
        + ", quantity="
        + quantity
        + ", status="
        + status
        + '}';
  }
}
