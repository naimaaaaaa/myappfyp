package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.UserExtra;


public interface UserExtraRepository extends JpaRepository<UserExtra, Long> {
    
}



// package com.example.demo.repository;

// import com.example.demo.model.UserExtra;

// import java.util.List;

// import org.springframework.data.repository.CrudRepository;

// public interface UserExtraRepository extends CrudRepository<UserExtra, Long> {
//     // You can add custom query methods here if needed
//       // Custom query method to find user extra profile by user ID
//       UserExtra findByUserId(Long userId);
      
//    // UserExtra findByUserId(Long userId);
//    void deleteByUserId(Long userId);

//    List<UserExtra> findSimilarUsers(String hobbies, String societies, String course, String ethnicity, String sports);


// }
