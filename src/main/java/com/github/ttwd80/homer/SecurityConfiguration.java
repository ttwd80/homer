package com.github.ttwd80.homer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class SecurityConfiguration {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new SCryptPasswordEncoder();
	}
}
