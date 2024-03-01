package com.epam.kafka.client.models;

import com.epam.kafka.client.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "app_order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
