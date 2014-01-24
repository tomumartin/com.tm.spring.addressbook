package com.blizzard.addressbook.controller;

import javax.validation.Valid;

import com.blizzard.addressbook.exception.DataNotFound;
import com.blizzard.addressbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blizzard.addressbook.entity.User;
import com.blizzard.addressbook.validation.UserValidator;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

/**
 * @author twmartin
 * @since 1/21/14
 */
@Controller
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private ResourceBundleMessageSource messageSource;

	@InitBinder
	private void initBinder(final WebDataBinder binder) {
		binder.setValidator(userValidator);
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createUserForm(final Model model) {
		model.addAttribute("user", new User());
		return "user-create";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String createUser(@ModelAttribute("user") @Valid final User user,
	                         final BindingResult result,
	                         final Locale locale,
	                         final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "user-create";
		}
		userService.create(user);
		redirectAttributes.addFlashAttribute("message", messageSource.getMessage("user.create.success",
				new Object[] {user.getUsername()}, locale));
		return "redirect:/user";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String listUsers(final Model model) {
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		return "user-list";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String deleteUser(@PathVariable final Integer id,
	                         final RedirectAttributes redirectAttributes,
	                         final Locale locale) {
		String message = null;
		try {
			final User user = userService.delete(id);
			message = messageSource.getMessage("user.delete.success",new Object[] {user.getUsername()}, locale);
		} catch(DataNotFound e) {
			message = messageSource.getMessage("user.not_exist", null, locale);
		}
		redirectAttributes.addFlashAttribute("message", message);

		return "redirect:/user";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editUserForm(final Model model,
	                           @PathVariable final Integer id,
	                           final RedirectAttributes redirectAttributes,
	                           final Locale locale) {
		final User user = userService.findById(id);
		if (user == null) {
			redirectAttributes.addFlashAttribute("message", messageSource.getMessage("user.not_exist", null, locale));
			return "redirect:/user";
		}

		model.addAttribute("user", user);
		return "user-edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String editUser(@ModelAttribute @Valid final User user,
	                           final BindingResult result,
	                           @PathVariable final Integer id,
	                           final RedirectAttributes redirectAttributes,
	                           final Locale locale) {
		if (result.hasErrors()) {
			return "user-edit";
		}

		String message = null;
		user.setId(id);
		try {
			final User updatedUser = userService.update(user);
			message = messageSource.getMessage("user.update.success",new Object[] {updatedUser.getUsername()}, locale);
		} catch(DataNotFound e) {
			message = messageSource.getMessage("user.not_exist", null, locale);
		}
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/user";
	}
}
