package com.joshua.app.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "confirmtoken")
public class ConfirmationToken {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id",nullable = false)
	private Long id;
	
	@Column(name = "token",nullable = false)
	private String token;
	
	@Column(name = "createdat",nullable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "confirmedat")
	private LocalDateTime confirmedAt;
	
	@Column(name = "expiresat",nullable = false)
	private LocalDateTime expiresAt;
	
	@ManyToOne
	@JoinColumn(name = "appuser_id",nullable = false)
	private User userId;

	public ConfirmationToken() {

	}

	public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt,
			User userId) {
		this.token = token;
		this.createdAt = createdAt;
		this.expiresAt = expiresAt;
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getConfirmedAt() {
		return confirmedAt;
	}

	public void setConfirmedAt(LocalDateTime confrimedAt) {
		this.confirmedAt = confrimedAt;
	}

	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

}
