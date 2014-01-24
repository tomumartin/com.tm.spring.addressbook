package com.blizzard.addressbook.validation;

import com.blizzard.addressbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.blizzard.addressbook.entity.User;

/**
 * @author twmartin
 * @since 1/21/14
 */
@Component
public class UserValidator implements Validator {

	@Autowired
	UserService userService;

	@Override
	public boolean supports(final Class<?> aClass) {
		return User.class.isAssignableFrom(aClass);
	}

	@Override
	public void validate(final Object target, final Errors errors) {
		final User user = (User) target;
		final String username = user.getUsername();
		if (StringUtils.isEmpty(username)) {
			errors.rejectValue("username", "user.username.blank", null);
		} else {
			final User existingUser = userService.findByUsername(username);
			if (existingUser != null && existingUser.getId() != user.getId()) {
				errors.rejectValue("username", "user.username.exists", new Object[] {username}, null);
			}
		}
	}
}
