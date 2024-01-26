package com.epam.springboot.crudrest.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "expenses")
@Getter
@Setter
@NoArgsConstructor
public class ExpenseEntity {

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String description;

  @Column private BigDecimal amount;

  @Column private Date date;

  public ExpenseEntity(String description, BigDecimal amount, Date date) {
    this.description = description;
    this.amount = amount;
    this.date = date;
  }
}
