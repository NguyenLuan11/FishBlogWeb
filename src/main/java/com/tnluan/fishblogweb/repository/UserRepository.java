package com.tnluan.fishblogweb.repository;

import com.tnluan.fishblogweb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);

    Optional<User> findByUserNameAndRole(String userName, String role);

    Optional<User> findByUserName(String userName);

}
