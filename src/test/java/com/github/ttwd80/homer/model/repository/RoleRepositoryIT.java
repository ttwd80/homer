package com.github.ttwd80.homer.model.repository;


import com.github.ttwd80.homer.model.entity.Role;
import com.github.ttwd80.homer.model.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class RoleRepositoryIT {

    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void testNotExist() {
        assertThat(roleRepository.findById("random-role")).isEmpty();
    }

    @Test
    void testInsert() {
        assertThat(roleRepository.findById("random-role")).isEmpty();
        var role = new Role();
        role.setRolename("random-role");
        roleRepository.save(role);
        assertThat(roleRepository.findById("random-role").get().getRolename()).isEqualTo("random-role");
    }

}
