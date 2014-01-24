//package com.blizzard.addressbook.config;
//
//import com.blizzard.addressbook.validation.UserValidator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
//import com.blizzard.addressbook.service.UserDetailsServiceImpl;
//import com.blizzard.addressbook.service.UserService;
//import com.blizzard.addressbook.service.UserServiceImplI;
//
///**
//* @author twmartin
//* @since 1/23/14
//*/
//@Configuration
//public class CoreConfig {
//
//	@Bean
//	public UserDetailsService userDetailsService() {
//		return new UserDetailsServiceImpl();
//	}
//
//	@Bean
//	public UserService userService() {
//		return new UserServiceImplI();
//	}
//
//	@Bean UserValidator userValidator() {
//		return new UserValidator();
//	}
//}
