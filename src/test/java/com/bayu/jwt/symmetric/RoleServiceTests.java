package com.bayu.jwt.symmetric;

import com.bayu.jwt.symmetric.model.Role;
import com.bayu.jwt.symmetric.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RoleServiceTests {

    private static final Logger log = LoggerFactory.getLogger(RoleServiceTests.class);

    @Autowired
    RoleRepository roleRepository;

    @Test
    void insertRoles() {
        Role roleUser = new Role("USER");
        Role roleAdmin = new Role("ADMIN");

        List<Role> roles = roleRepository.saveAll(List.of(roleUser, roleAdmin));

        for (Role role : roles) {
            log.info("ID : {}", role.getId());
            log.info("Authority : {}", role.getAuthority());
        }
    }

}
