package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Society;

import java.util.List;

@Repository
public interface SocietyRepository extends JpaRepository<Society, Long> {
    List<Society> findByUserId(Long userId);
}
