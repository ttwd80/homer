package com.github.ttwd80.homer.model.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class User {

	@Id
	private String username;

	@Column
	private String password;

	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "rolename"), inverseJoinColumns = @JoinColumn(name = "username"))
	private Set<Role> roles;
}
