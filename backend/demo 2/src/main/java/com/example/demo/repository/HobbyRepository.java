package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Hobby;

import java.util.List;

@Repository
public interface HobbyRepository extends JpaRepository<Hobby, Long> {
    List<Hobby> findByUserId(Long userId);
}
