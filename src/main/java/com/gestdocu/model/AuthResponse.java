package com.gestdocu.model;

import java.util.List;

public class AuthResponse {
	private String token;
	private List<String> roles;
	
	public AuthResponse(String token, List<String> roles) {
		super();
		this.token = token;
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}	
}
