package com.epam.kafka.client.dtos;

import com.epam.kafka.client.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDTO {
  private Long id;
  private String flavor;
  private int quantity;
  private OrderStatus status;

  @Override
  public String toString() {
    return "OrderDTO{"
        + "id="
        + id
        + ", flavor='"
        + flavor
        + '\''
        + ", quantity="
        + quantity
        + ", status="
        + status
        + '}';
  }
}
