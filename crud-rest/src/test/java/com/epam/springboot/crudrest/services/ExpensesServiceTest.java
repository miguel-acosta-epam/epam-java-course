package com.epam.springboot.crudrest.services;

import com.epam.springboot.crudrest.models.ExpenseEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ExpensesServiceTest {

  @Autowired private ExpenseService expenseService;

  @Test
  @DisplayName("Should get all expenses from the database")
  public void shouldGetAllExpensesFromDB() {
    // Act
    List<ExpenseEntity> result = expenseService.getAllExpenses();

    // Assert
    assertEquals(5, result.size());
  }

  @Test
  @DisplayName("Should get an expense by ID from the database")
  public void shouldGetByIdExpensesFromDB() throws Exception {
    // Act
    ExpenseEntity result = expenseService.getExpenseById(5L);

    // Assert
    assertNotNull(result);
    assertEquals("Coffee with colleagues", result.getDescription());
  }

  @Test
  @DisplayName("Should get the total expenses amount")
  public void shouldGetExpensesTotal() {
    // Act
    BigDecimal total = expenseService.getExpensesTotal();

    // Assert
    assertEquals(BigDecimal.valueOf(225.5).doubleValue(), total.doubleValue());
  }
}
