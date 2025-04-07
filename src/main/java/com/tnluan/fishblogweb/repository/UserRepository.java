package com.tnluan.fishblogweb.repository;

import com.tnluan.fishblogweb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.userName = :userName AND u.password = :password")
    Optional<User> loginAccountUser(@Param("userName") String userName, @Param("password") String password);
}
