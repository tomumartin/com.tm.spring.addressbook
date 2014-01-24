package com.blizzard.addressbook.controller;

import java.util.List;
import java.util.Locale;

import com.blizzard.addressbook.validation.ContactValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blizzard.addressbook.entity.Contact;
import com.blizzard.addressbook.entity.User;
import com.blizzard.addressbook.exception.DataNotFound;
import com.blizzard.addressbook.service.ContactService;
import com.blizzard.addressbook.service.UserService;

import javax.validation.Valid;

/**
 * @author twmartin
 * @since 1/23/14
 */
@Controller
@RequestMapping(value = "/contact")
public class ContactController {

	@Autowired
	private UserService userService;

	@Autowired
	private ContactService contactService;

	@Autowired
	private ContactValidator contactValidator;

	@Autowired
	private ResourceBundleMessageSource messageSource;

	@InitBinder
	private void initBinder(final WebDataBinder binder) {
		binder.setValidator(contactValidator);
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String createContactForm(final Model model) {
		model.addAttribute("contact", new Contact());
		return "contact-create";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String createContact(@ModelAttribute("contact") @Valid final Contact contact,
	                         final BindingResult result,
	                         final Locale locale,
	                         final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "contact-create";
		}
		contact.setUser(getUser());
		contactService.create(contact);
		redirectAttributes.addFlashAttribute("message", messageSource.getMessage("contact.create.success",
				new Object[]{getContactName(contact)}, locale));
		return "redirect:/contact";
	}

	@RequestMapping(method = RequestMethod.GET)
	@Transactional
	public String listContacts(final Model model) {
		final List<Contact> contacts = getContacts();
		model.addAttribute("contacts", contacts);
		return "contact-list";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String deleteContact(@PathVariable final Integer id, final RedirectAttributes redirectAttributes,
			final Locale locale) {
		String message = null;
		try {
			final Contact contact = contactService.delete(id);
			message = messageSource.getMessage("contact.delete.success", new Object[] {contact}, locale);
		} catch (DataNotFound e) {
			message = messageSource.getMessage("contact.not_exist", null, locale);
		}
		redirectAttributes.addFlashAttribute("message", message);

		return "redirect:/contact";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editUserForm(final Model model,
	                           @PathVariable final Integer id,
	                           final RedirectAttributes redirectAttributes,
	                           final Locale locale) {
		final Contact contact = contactService.findById(id);
		if (contact == null) {
			redirectAttributes.addFlashAttribute("message", messageSource.getMessage("contact.not_exist", null, locale));
			return "redirect:/contact";
		}

		model.addAttribute("contact", contact);
		return "contact-edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String editUser(@ModelAttribute @Valid final Contact contact,
	                       final BindingResult result,
	                       @PathVariable final Integer id,
	                       final RedirectAttributes redirectAttributes,
	                       final Locale locale) {
		if (result.hasErrors()) {
			return "contact-edit";
		}

		String message = null;
		contact.setId(id);
		try {
			final Contact updatedContact = contactService.update(contact);
			message = messageSource.getMessage("contact.update.success",new Object[] {getContactName(updatedContact)}, locale);
		} catch(DataNotFound e) {
			message = messageSource.getMessage("contact.not_exist", null, locale);
		}
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/contact";
	}

	/**
	 * Get contacts of logged in user.
	 * 
	 * @return contacts of logged in user.
	 */
	private List<Contact> getContacts() {
		final User user = getUser();
		// force jpa to query contacts
		final List<Contact> contacts = user.getContacts();
		contacts.size();
		return contacts;
	}

	/**
	 * Get logged in user.
	 *
	 * @return logged in user.
	 */
	private User getUser() {
		final UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return userService.findByUsername(principal.getUsername());
	}

	/**
	 * Helper method to convert first and last name into a single string separated by a space.
	 *
	 * @param contact
	 * @return converted first and last name into a single string separated by a space.
	 */
	private String getContactName(Contact contact) {
		final StringBuilder sb = new StringBuilder();
		final String firstName = contact.getFirstName();
		final String lastName = contact.getLastName();
		final boolean lastNameHasText = StringUtils.hasText(lastName);
		if (StringUtils.hasText(firstName)) {
			sb.append(firstName);
			if (lastNameHasText) {
				sb.append(" ");
			}
		}
		if(lastNameHasText) {
			sb.append(lastName);
		}
		return sb.toString();
	}
}
