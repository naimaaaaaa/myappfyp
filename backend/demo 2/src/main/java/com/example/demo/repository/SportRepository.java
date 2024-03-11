package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Sport;

import java.util.List;

@Repository
public interface SportRepository extends JpaRepository<Sport, Long> {
    List<Sport> findByUserId(Long userId);
}
