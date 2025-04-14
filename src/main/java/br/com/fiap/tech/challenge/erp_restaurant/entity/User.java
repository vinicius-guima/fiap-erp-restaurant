package br.com.fiap.tech.challenge.erp_restaurant.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String name;

	@Id
	@Column(unique = true)
	private String email;

	private String login;

	private String password;

	private LocalDateTime lastupdate;

	public User() {
	}

	public User(String name, String email, String login, String password) {
		super();
		this.name = name;
		this.email = email;
		this.login = login;
		this.password = password;
		this.lastupdate = LocalDateTime.now();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getLastupdate() {
		return lastupdate;
	}

}
