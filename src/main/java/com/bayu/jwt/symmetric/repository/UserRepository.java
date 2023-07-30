package com.bayu.jwt.symmetric.repository;

import com.bayu.jwt.symmetric.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findFirstByUsername(String username);
}
