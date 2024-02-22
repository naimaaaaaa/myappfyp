package com.example.demo.repository;

import com.example.demo.model.UserExtra;
import org.springframework.data.repository.CrudRepository;

public interface UserExtraRepository extends CrudRepository<UserExtra, Long> {
    // You can add custom query methods here if needed
   // UserExtra findByUserId(Long userId);
}
