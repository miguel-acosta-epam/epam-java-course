package com.epam.springboot.crudrest.controllers;

import com.epam.springboot.crudrest.models.ExpenseEntity;
import com.epam.springboot.crudrest.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
  @Autowired private ExpenseService expenseService;

  @GetMapping
  public List<ExpenseEntity> getAllExpenses() {
    return expenseService.getAllExpenses();
  }

  @GetMapping("/{id}")
  public ExpenseEntity getExpenseById(@PathVariable Long id) throws Exception {
    return expenseService.getExpenseById(id);
  }

  @PostMapping
  public ExpenseEntity createExpense(@RequestBody ExpenseEntity expense) {
    return expenseService.createExpense(expense);
  }

  @PatchMapping
  public ExpenseEntity updateExpense(@RequestBody ExpenseEntity expense) throws Exception {
    return expenseService.updateExpense(expense);
  }

  @DeleteMapping("/{id}")
  public void deleteExpense(@PathVariable Long id) throws Exception {
    expenseService.deleteExpense(id);
  }
}
