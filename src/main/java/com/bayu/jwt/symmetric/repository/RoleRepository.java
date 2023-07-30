package com.bayu.jwt.symmetric.repository;

import com.bayu.jwt.symmetric.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findFirstByAuthority(String authorityName);
}
