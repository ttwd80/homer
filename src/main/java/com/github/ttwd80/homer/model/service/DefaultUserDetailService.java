package com.github.ttwd80.homer.model.service;

import com.github.ttwd80.homer.model.entity.Role;
import com.github.ttwd80.homer.model.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

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
            var authorities = user.getRoles().stream().map(Role::getRolename).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            var userBuilder = org.springframework.security.core.userdetails.User.builder();
            userBuilder.username(user.getUsername()).password(user.getPassword()).authorities(authorities);
            return userBuilder.build();
        }

    }

}
