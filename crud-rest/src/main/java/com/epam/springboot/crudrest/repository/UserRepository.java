package com.epam.springboot.crudrest.repository;

import com.epam.springboot.crudrest.models.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {
}
