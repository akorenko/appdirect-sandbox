package com.devexperts.appdirect.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class SimpleAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private SimpleUserDetailsService userService;

	@Override
	public Authentication authenticate(Authentication authentication) {
		String username = authentication.getName();
		Object credentials = authentication.getCredentials();
		User user = userService.loadUserByUsername(username);
		if (user == null) {
			throw new BadCredentialsException("Incorrect username: " + username);
		}
		if (!credentials.equals(user.getPassword())) {
			throw new BadCredentialsException("Incorrect password");
		}

		return new UsernamePasswordAuthenticationToken(user, credentials, user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}
}
