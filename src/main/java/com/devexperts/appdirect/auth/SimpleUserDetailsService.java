package com.devexperts.appdirect.auth;

import com.devexperts.appdirect.storage.UserStorage;
import com.devexperts.appdirect.to.UserData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SimpleUserDetailsService implements UserDetailsService {
	private static final Logger logger = Logger.getLogger(SimpleUserDetailsService.class);

	@Autowired
	private UserStorage userStorage;

	@Override
	public User loadUserByUsername(final String username) {
		logger.info("Trying to load user:  " + username);
		UserData user = userStorage.findByOpenId(username);
		if (user == null) {
			throw new BadCredentialsException("Incorrect username: " + username);
		}

		return new User(username, "", AuthorityUtils.createAuthorityList("ROLE_USER"));
	}
}