package it.flyering.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import freemarker.template.TemplateException;
import it.flyering.dao.UserDAO;
import it.flyering.model.User;
import it.flyering.service.ConverterHelper;
import it.flyering.service.EmailService;
import it.flyering.service.UserService;
import it.flyering.utils.Constants;

@Controller
//@Slf4j
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private ConverterHelper converterHelper;

	@Autowired
	private EmailService emailService;

	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value={"/access-denied"}, method = RequestMethod.GET)
	public ModelAndView accessDenied(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("access-denied");
		return modelAndView;
	}

	@RequestMapping(value="/registration-user", method = RequestMethod.GET)
	public ModelAndView registrationUser(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration-user");
		return modelAndView;
	}

	@RequestMapping(value="/registration-admin", method = RequestMethod.GET)
	public ModelAndView registrationAdmin(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration-admin");
		return modelAndView;
	}

	@RequestMapping(value = "/registrationAdmin", method = RequestMethod.POST)
	public ModelAndView createNewAdmin(@Valid User user, BindingResult bindingResult) throws MessagingException, IOException, TemplateException {
		ModelAndView modelAndView = new ModelAndView();
		UserDAO userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
			.rejectValue("email", "error.user",
					"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration-admin");
		} else {
			userService.saveUser(converterHelper.convertUserToUserDAO(user), Constants.ROLE_ADMIN);
			emailService.sendAdminRegistrationCompletedEmail(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration-admin");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/registrationUser", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) throws MessagingException, IOException, TemplateException {
		ModelAndView modelAndView = new ModelAndView();
		UserDAO userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
			.rejectValue("email", "error.user",
					"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration-user");
		} else {
			userService.saveUser(converterHelper.convertUserToUserDAO(user), Constants.ROLE_USER);
			emailService.sendUserRegistrationCompletedEmail(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration-user");
		}
		return modelAndView;
	}

	@RequestMapping("/default")
	public String defaultAfterLogin() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		for(GrantedAuthority ga : userDetails.getAuthorities()) {
			if (Constants.ROLE_ADMIN.equals(ga.getAuthority())) {
				return "redirect:/admin/home";
			} else if (Constants.ROLE_USER.equals(ga.getAuthority())) {
				return "redirect:/user/home";
			}
		}
		return "redirect:/";
	}
}
