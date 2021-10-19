package com.abhi.learning.spring.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/* Filter to verify jwt in header of every request */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		/*System.out.println("JwtAuthFilter.doFilterInternal()");
		String header = request.getHeader("Authorization");
		System.out.println("header : " + header);
		final String jwt = request.getHeader("Authorization").split(" ")[1];
		System.out.println("jwt : " + jwt);*/
		// exract username from jwt and verify the same
		filterChain.doFilter(request, response);
	}

}
