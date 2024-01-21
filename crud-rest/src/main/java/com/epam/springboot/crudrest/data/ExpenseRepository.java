package com.epam.springboot.crudrest.data;

import com.epam.springboot.crudrest.models.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {}
