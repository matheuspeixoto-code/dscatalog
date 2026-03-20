package com.dscatalog.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.dscatalog.model.User;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDTO implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@Size(min = 5,max = 60,message = "Deve ter entre 5 e 60 caracteres")
	@NotBlank(message = "Campo obrigatorio")
	private String firstName;
	
	private String lastName;
	
	@Email(message = "Inserir um email valido")
	private String email;
	
	
	private String password;
	
	Set<RoleDTO> roles = new HashSet<>();

	
	
	public Set<RoleDTO> getRoles() {
		return roles;
	}

	public UserDTO() {
		super();
	}
	
	public UserDTO(User entity) {
		super();
		this.id = entity.getId();
		this.firstName = entity.getFirstName();
		this.lastName = entity.getLastName();
		this.email = entity.getEmail();
		
		entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
	}
	
	public UserDTO(Long id, String firstName, String lastName, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
