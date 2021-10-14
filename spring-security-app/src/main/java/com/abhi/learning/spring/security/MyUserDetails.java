package com.abhi.learning.spring.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private String userName;

	public MyUserDetails() {
	}

	public MyUserDetails(String userName) {
		this.userName = userName;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		GrantedAuthority auth = new SimpleGrantedAuthority("ROLE_ADMIN");
		Collection<GrantedAuthority> list = new ArrayList<>();
		list.add(auth);
		return list;
	}

	@Override
	public String getPassword() {
		return "$2a$10$zXRzqDlIHSoe7oiSew1.weKPLVt9h1m6BbGk4YdndqUTq04zivHdC";
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
