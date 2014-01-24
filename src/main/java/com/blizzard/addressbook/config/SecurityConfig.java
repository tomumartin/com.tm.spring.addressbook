package com.blizzard.addressbook.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
* @author twmartin
* @since 1/22/14
*/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/contact", true)
				.loginProcessingUrl("/login")
				.failureUrl("/login/fail")
				.permitAll()
				.and()
			.logout()
				.invalidateHttpSession(true)
				.logoutSuccessUrl("/logout/success")
				.permitAll()
				.and()
			.httpBasic()
				.and()
			.authorizeRequests()
				.anyRequest().authenticated();
	}
}
