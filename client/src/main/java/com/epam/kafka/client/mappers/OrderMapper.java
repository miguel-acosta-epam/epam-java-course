package com.epam.kafka.client.mappers;

import com.epam.kafka.client.dtos.OrderDTO;
import com.epam.kafka.client.enums.OrderStatus;
import com.epam.kafka.client.models.OrderEntity;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class OrderMapper {

  public OrderEntity mapOrderDTOToEntity(OrderDTO orderDTO) {
    return OrderEntity.builder()
        .flavor(orderDTO.getFlavor())
        .status(orderDTO.getStatus())
        .quantity(orderDTO.getQuantity())
        .build();
  }
}
