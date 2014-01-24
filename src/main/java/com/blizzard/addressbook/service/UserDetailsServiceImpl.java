package com.blizzard.addressbook.service;

import com.blizzard.addressbook.entity.User;
import com.blizzard.addressbook.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author twmartin
 * @since 1/23/14
 *
 * Used for spring authentication.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final User user = userService.findByUsername(username);
		if(user == null) {
			return null;
		}
		return UserDetailsImpl.fromUser(user);
	}
}
