package com.blizzard.addressbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blizzard.addressbook.entity.User;

import java.util.Locale;

/**
 * @author twmartin
 * @since 1/23/14
 */
@Controller
public class SessionController {

	@Autowired
	private ResourceBundleMessageSource messageSource;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm(final Model model) {
		model.addAttribute("user", new User());
		return "login";
	}

	@RequestMapping(value = "/login/fail", method = RequestMethod.GET)
	public String loginFail(final Locale locale,
	                        final RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", messageSource.getMessage("login.fail", null, locale));
		return "redirect:/login";
	}

	@RequestMapping(value = "/logout/success", method = RequestMethod.GET)
	public String logoutSuccess(final Locale locale,
	                     final RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", messageSource.getMessage("logout.success", null, locale));
		return "redirect:/login";
	}
}
