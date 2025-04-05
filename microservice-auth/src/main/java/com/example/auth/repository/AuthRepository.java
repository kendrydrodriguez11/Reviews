package com.example.auth.repository;

import com.example.auth.model.EntityUser;
import org.springframework.data.repository.CrudRepository;

public interface AuthRepository extends CrudRepository<EntityUser, Long> {
}
