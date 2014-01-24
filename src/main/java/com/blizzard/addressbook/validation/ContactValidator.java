package com.blizzard.addressbook.validation;

import com.blizzard.addressbook.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.blizzard.addressbook.entity.User;
import com.blizzard.addressbook.service.UserService;

/**
 * @author twmartin
 * @since 1/21/14
 */
@Component
public class ContactValidator implements Validator {

	@Override
	public boolean supports(final Class<?> aClass) {
		return Contact.class.isAssignableFrom(aClass);
	}

	@Override
	public void validate(final Object target, final Errors errors) {
		final Contact contact = (Contact) target;
		if (StringUtils.isEmpty(contact.getFirstName()) && StringUtils.isEmpty(contact.getLastName())) {
			errors.rejectValue("firstName", "contact.name.blank", null);
		}
	}
}
