// package com.example.demo.repository;

// import com.example.demo.model.UserConnections;
// import com.example.demo.model.UserExtra;

// import java.util.List;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
// import org.springframework.stereotype.Repository;

// @Repository
// public interface UserConnectionsRepository extends JpaRepository<UserConnections, Long> {
    
//     @Query("SELECT uc.user FROM UserConnections uc WHERE uc.user.id != :userId " +
//             "AND uc.user.id IN (SELECT ue.user.id FROM UserExtra ue " +
//             "WHERE ue.hobbies IN :hobbies " +
//             "AND ue.societies IN :societies " +
//             "AND ue.course = :course " +
//             "AND ue.ethnicity = :ethnicity " +
//             "AND ue.sports IN :sports)")
//     List<UserExtra> findSimilarUsers(
//             @Param("userId") Long userId,
//             @Param("hobbies") List<String> hobbies,
//             @Param("societies") List<String> societies,
//             @Param("course") String course,
//             @Param("ethnicity") String ethnicity,
//             @Param("sports") List<String> sports);

//             UserConnections findByUserId(Long userId);

// }
