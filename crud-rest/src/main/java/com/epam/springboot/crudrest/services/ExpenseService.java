package com.epam.springboot.crudrest.services;

import com.epam.springboot.crudrest.data.ExpenseRepository;
import com.epam.springboot.crudrest.models.ExpenseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
  @Autowired private ExpenseRepository expenseRepository;

  public List<ExpenseEntity> getAllExpenses() {
    return expenseRepository.findAll();
  }

  public ExpenseEntity getExpenseById(Long id) throws Exception {
    var expenseOptional = expenseRepository.findById(id);

    return expenseOptional.orElseThrow(() -> new Exception("Expense with id:" + id + " not found"));
  }

  public ExpenseEntity createExpense(ExpenseEntity expense) {
    return expenseRepository.save(expense);
  }

  public ExpenseEntity updateExpense(ExpenseEntity expense) throws Exception {

    if (expense.getId() == null) {
      throw new Exception("No id defined");
    }

    return expenseRepository.save(expense);
  }

  public void deleteExpense(Long id) throws Exception {
    var expenseOptional = expenseRepository.findById(id);

    var expense =
        expenseOptional.orElseThrow(() -> new Exception("Expense with id:" + id + " not found."));

    expenseRepository.delete(expense);
  }
}
