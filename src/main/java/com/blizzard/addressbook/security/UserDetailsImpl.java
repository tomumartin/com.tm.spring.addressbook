package com.blizzard.addressbook.security;

import com.blizzard.addressbook.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author twmartin
 * @since 1/23/14
 */
public class UserDetailsImpl implements UserDetails {
	private Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
	private String username;
	private String password;
	private boolean accountNonLocked = true;
	private boolean accountNonExpired = true;
	private boolean credentialsNonExpired = true;
	private boolean enabled = true;

	private UserDetailsImpl() {}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	private void setAuthorities(Set<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	private void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	private void setUsername(String username) {
		this.username = username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	private void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	private void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	private void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	private void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public static UserDetails fromUser(final User user) {
		final UserDetailsImpl userDetails = new UserDetailsImpl();
		userDetails.setUsername(user.getUsername());
		userDetails.setPassword(user.getPassword());
		userDetails.setEnabled(user.isActive());
		userDetails.setAccountNonLocked(!user.isLocked());
		return userDetails;
	}
}
