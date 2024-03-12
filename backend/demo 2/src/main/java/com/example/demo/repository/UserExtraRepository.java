

package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.UserExtra;


public interface UserExtraRepository extends JpaRepository<UserExtra, Long> {
    //
   // UserExtra findByUserId(Long userId);
    
//
}


