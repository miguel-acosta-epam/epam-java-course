package com.epam.kafka.client.models;

import com.epam.kafka.client.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "app_order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String flavor;

  @Column private int quantity;

  @Column
  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  @Override
  public String toString() {
    return "Order [id="
        + id
        + ", pizzaType="
        + flavor
        + ", quantity="
        + quantity
        + ", status="
        + status
        + "]";
  }
}
