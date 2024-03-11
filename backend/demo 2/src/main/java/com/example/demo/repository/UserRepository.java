package com.example.demo.repository;

import java.util.Optional;

import com.example.demo.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
// package com.example.demo.repository;

// import com.example.demo.model.User;
// import org.springframework.data.repository.CrudRepository;


// public interface UserRepository extends CrudRepository<User,Long>{
// 	User findByEmail(String email);
// }

