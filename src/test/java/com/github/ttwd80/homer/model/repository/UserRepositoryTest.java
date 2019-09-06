package com.github.ttwd80.homer.model.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.github.ttwd80.homer.model.entity.User;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

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

}
