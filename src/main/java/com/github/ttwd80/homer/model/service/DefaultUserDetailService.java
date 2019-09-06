package com.github.ttwd80.homer.model.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.ttwd80.homer.model.repository.UserRepository;

@Service
public class DefaultUserDetailService implements UserDetailsService {

	private final UserRepository userRepository;

	public DefaultUserDetailService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var record = userRepository.findById(username);
		if (record.isEmpty()) {
			throw new UsernameNotFoundException(username);
		} else {
			var user = record.get();
			var userBuilder = org.springframework.security.core.userdetails.User.builder();
			userBuilder.username(user.getUsername()).password(user.getPassword());
			return userBuilder.build();
		}

	}

}
