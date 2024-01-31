package com.epam.springboot.crudrest.controllers;

import com.epam.springboot.crudrest.models.ExpenseEntity;
import com.epam.springboot.crudrest.services.ExpenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ExpenseControllerTest {

  @Mock private ExpenseService expenseService;

  @InjectMocks private ExpenseController expenseController;

  private MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(expenseController).build();
  }

  @Test
  @DisplayName("GET /api/expenses - Should return all expenses")
  public void getAllExpenses() throws Exception {
    // Arrange
    List<ExpenseEntity> expenses =
        Arrays.asList(
            new ExpenseEntity(1L, "Groceries", BigDecimal.valueOf(50.00), new Date()),
            new ExpenseEntity(2L, "Dinner with friends", BigDecimal.valueOf(30.00), new Date()));
    when(expenseService.getAllExpenses()).thenReturn(expenses);

    // Act
    ResultActions result = mockMvc.perform(get("/api/expenses"));

    // Assert
    result
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.length()").value(expenses.size()));
  }

  @Test
  @DisplayName("GET /api/expenses/{id} - Should return an expense by ID")
  public void getExpenseById() throws Exception {
    // Arrange
    ExpenseEntity expense =
        new ExpenseEntity(5L, "Coffee with colleagues", BigDecimal.valueOf(10.50), new Date());
    when(expenseService.getExpenseById(anyLong())).thenReturn(expense);

    // Act
    ResultActions result = mockMvc.perform(get("/api/expenses/{id}", 5L));

    // Assert
    result
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(expense.getId()))
        .andExpect(jsonPath("$.description").value(expense.getDescription()));
  }

  @Test
  @DisplayName("GET /api/expenses/total - Should return total expenses amount")
  public void getExpensesTotal() throws Exception {
    // Arrange
    BigDecimal totalAmount = BigDecimal.valueOf(225.5);
    when(expenseService.getExpensesTotal()).thenReturn(totalAmount);

    // Act
    ResultActions result = mockMvc.perform(get("/api/expenses/total"));

    // Assert
    result
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$").value(totalAmount.doubleValue()));
  }
}
