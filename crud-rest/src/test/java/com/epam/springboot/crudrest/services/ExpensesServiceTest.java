package com.epam.springboot.crudrest.services;

import com.epam.springboot.crudrest.data.ExpenseRepository;
import com.epam.springboot.crudrest.models.ExpenseEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ExpensesServiceTest {

  @Mock private ExpenseRepository expenseRepository;

  @Autowired private ExpenseService expenseService;

  @Test
  public void getAllExpenses() {
    // Arrange
    ExpenseEntity expense1 = new ExpenseEntity();
    expense1.setId(1L);
    expense1.setDescription("Groceries");
    // Set other properties

    ExpenseEntity expense2 = new ExpenseEntity();
    expense2.setId(2L);
    expense2.setDescription("Dinner with friends");
    // Set other properties

    List<ExpenseEntity> expenses = Arrays.asList(expense1, expense2);

    // Mocking the repository's findAll() method
    when(expenseRepository.findAll()).thenReturn(expenses);

    // Act
    List<ExpenseEntity> result = expenseService.getAllExpenses();

    // Assert
    assertEquals(expenses, result);
  }
}
