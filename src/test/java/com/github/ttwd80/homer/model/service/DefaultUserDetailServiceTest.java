package com.github.ttwd80.homer.model.service;

import com.github.ttwd80.homer.model.entity.User;
import com.github.ttwd80.homer.model.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DefaultUserDetailServiceTest {

    private DefaultUserDetailService sut;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        sut = new DefaultUserDetailService(userRepository);
    }

    @Test()
    void loadUserByUsernameNotFound() {
        when(userRepository.findById("not-found")).thenThrow(new UsernameNotFoundException("not-found"));
        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            sut.loadUserByUsername("not-found");
        });
    }

    @Test
    void loadUserByUsername() {
        var user = new User();
        user.setUsername("normal-user");
        user.setPassword("random-password");
        when(userRepository.findById("normal-user")).thenReturn(Optional.ofNullable(user));
        assertThat(sut.loadUserByUsername("normal-user").getPassword()).isEqualTo("random-password");
    }
}