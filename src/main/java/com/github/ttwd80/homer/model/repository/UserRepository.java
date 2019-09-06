package com.github.ttwd80.homer.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.ttwd80.homer.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
