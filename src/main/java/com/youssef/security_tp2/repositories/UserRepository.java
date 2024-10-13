package com.youssef.security_tp2.repositories;

import com.youssef.security_tp2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String s);
    boolean existsByUsername(String username);
}
