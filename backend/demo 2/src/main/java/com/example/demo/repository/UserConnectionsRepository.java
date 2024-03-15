package com.example.demo.repository;

import com.example.demo.model.User;
import com.example.demo.model.UserConnections;
import com.example.demo.model.UserExtra;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserConnectionsRepository extends JpaRepository<UserConnections, Long> {
    
//     @Query("SELECT DISTINCT uc.user FROM UserConnections uc " +
//             "JOIN uc.user.userExtra ue " +
//             "JOIN uc.user.hobbies h " +
//             "JOIN uc.user.societies s " +
//             "JOIN uc.user.sports sp " +
//             "WHERE uc.user.id != :userId " +
//             "AND h.name IN :hobbies " +
//             "AND s.name IN :societies " +
//             "AND ue.course = :course " +
//             "AND ue.ethnicity = :ethnicity " +
//             "AND sp.name IN :sports") 
//     List<User> findSimilarUsers(
//             @Param("userId") Long userId,
//             @Param("hobbies") List<String> hobbies,
//             @Param("societies") List<String> societies,
//             @Param("course") String course,
//             @Param("ethnicity") String ethnicity,
//             @Param("sports") List<String> sports);
@Query("SELECT DISTINCT uc.user FROM UserConnections uc " +
            "JOIN uc.user.userExtra ue " +
            "JOIN uc.user.hobbies h " +
            "JOIN uc.user.societies s " +
            "JOIN uc.user.sports sp " +
            "WHERE uc.user.id != :userId " +
            "AND h.name IN :hobbies " +
            "AND s.name IN :societies " +
            "AND ue.course = :course " +
            "AND ue.ethnicity = :ethnicity " +
            "AND sp.name IN :sports") 
List<User> findSimilarUsers(
        @Param("userId") Long userId,
        @Param("hobbies") List<String> hobbies,
        @Param("societies") List<String> societies,
        @Param("course") String course,
        @Param("ethnicity") String ethnicity,
        @Param("sports") List<String> sports);

    UserConnections findByUserId(Long userId);
}
