package com.github.ttwd80.homer.model.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.ttwd80.homer.model.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.github.ttwd80.homer.model.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Transactional
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class UserRepositoryIT {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void testNotExist() {
        assertThat(userRepository.findById("random-id")).isEmpty();
    }

    @Test
    void testInsert() {
        assertThat(userRepository.findById("random-id")).isEmpty();
        var user = new User();
        user.setUsername("random-id");
        user.setPassword("random-password");
        userRepository.save(user);
        assertThat(userRepository.findById("random-id").get().getPassword()).isEqualTo("random-password");
    }

    @Test
    void testInsertMultipleRoles() {
        {
            var role = new Role();
            role.setRolename("ROLE_ADMIN");
            roleRepository.save(role);
        }
        {
            var role = new Role();
            role.setRolename("ROLE_USER");
            roleRepository.save(role);
        }
        assertThat(userRepository.findById("random-id")).isEmpty();
        var user = new User();
        user.setUsername("random-id");
        user.setPassword("random-password");
        var roles = new HashSet<Role>();
        {
            var role = new Role();
            role.setRolename("ROLE_ADMIN");
            roles.add(role);
        }
        {
            var role = new Role();
            role.setRolename("ROLE_USER");
            roles.add(role);
        }
        user.setRoles(roles);
        userRepository.save(user);
        assertThat(userRepository.findById("random-id").get().getRoles().size()).isEqualTo(2);
    }

}
