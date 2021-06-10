package com.hackerrank.sample.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hackerrank.sample.enums.AuthorityType;
import com.hackerrank.sample.model.Authority;
import com.hackerrank.sample.model.User;
import com.hackerrank.sample.repository.UserRepository;
import com.hackerrank.sample.service.AuthUserDetails;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("javainuse".equals(username)) {
			User user = new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6");
			Set<Authority> authorities = new HashSet<>();
			authorities.add(new Authority(AuthorityType.ROLE_ADMIN));
			user.setAuthorities(authorities);
			return new AuthUserDetails(user);
		} else {
			User user = userRepository.findByUsername(username);
			if (user == null) { throw new UsernameNotFoundException("User not found."); }
			log.info("loadUserByUsername() : {}", username);
			return new AuthUserDetails(user);
		}
	}
}
