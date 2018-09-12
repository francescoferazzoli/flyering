package it.flyering.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import freemarker.template.TemplateException;
import it.flyering.dao.UserDAO;
import it.flyering.model.ChangePassword;
import it.flyering.model.ResetPassword;
import it.flyering.model.User;
import it.flyering.service.ConverterHelper;
import it.flyering.service.EmailService;
import it.flyering.service.UserService;
import it.flyering.utils.Constants;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private ConverterHelper converterHelper;

	@Autowired
	private EmailService emailService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
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

	@RequestMapping(value={"/access-denied"}, method = RequestMethod.GET)
	public ModelAndView accessDenied(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("access-denied");
		return modelAndView;
	}

	@RequestMapping(value="/registration-user", method = RequestMethod.GET)
	public ModelAndView registrationUserPage(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", new User());
		modelAndView.setViewName("registration-user");
		return modelAndView;
	}

	@RequestMapping(value="/registration-admin", method = RequestMethod.GET)
	public ModelAndView registrationAdminPage(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", new User());
		modelAndView.setViewName("registration-admin");
		return modelAndView;
	}

	@RequestMapping(value = "/registrationAdmin", method = RequestMethod.POST)
	public ModelAndView registrationAdminAction(@Valid User user, BindingResult bindingResult) throws MessagingException, IOException, TemplateException {
		ModelAndView modelAndView = new ModelAndView();
		UserDAO userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
			.rejectValue("email", "error.user",
					"There is already a user registered with the email provided");
		} else if(!bindingResult.hasErrors()) {
			userService.saveUser(converterHelper.convertUserToUserDAO(user), Constants.ROLE_ADMIN);
			emailService.sendAdminRegistrationCompletedEmail(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
		}

		modelAndView.setViewName("registration-admin");
		return modelAndView;
	}

	@RequestMapping(value = "/registrationUser", method = RequestMethod.POST)
	public ModelAndView registrationUserAction(@Valid User user, BindingResult bindingResult) throws MessagingException, IOException, TemplateException {
		ModelAndView modelAndView = new ModelAndView();
		UserDAO userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
			.rejectValue("email", "error.user",
					"There is already a user registered with the email provided");
		} else if(!bindingResult.hasErrors()) {
			userService.saveUser(converterHelper.convertUserToUserDAO(user), Constants.ROLE_USER);
			emailService.sendUserRegistrationCompletedEmail(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
		}

		modelAndView.setViewName("registration-user");
		return modelAndView;
	}



	@RequestMapping(value="/reset-password", method = RequestMethod.GET)
	public ModelAndView resetPasswordPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("resetPassword", new ResetPassword());
		modelAndView.setViewName("reset-password");
		return modelAndView;
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public ModelAndView resetPasswordAction(@Valid ResetPassword resetPassword, BindingResult bindingResult) throws MessagingException, IOException, TemplateException {
		ModelAndView modelAndView = new ModelAndView();
		UserDAO userExists = userService.findUserByEmail(resetPassword.getEmail());
		if (userExists == null) {
			bindingResult
			.rejectValue("email", "error.resetPassword",
					"There is not an user registered with the email provided");
		} else if(!bindingResult.hasErrors()) {
			String generatedPassword = RandomStringUtils.randomAlphabetic(10);
			userExists.setPassword(generatedPassword);
			emailService.sendResetPassword(userExists);
			userService.updatePassword(userExists);

			modelAndView.addObject("successMessage", "Password reset successfully");
			modelAndView.addObject("resetPassword", new ResetPassword());
		}

		modelAndView.setViewName("reset-password");
		return modelAndView;
	}

	@RequestMapping(value="/change-password", method = RequestMethod.GET)
	public ModelAndView changePasswordPage(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("changePassword", new ChangePassword());
		modelAndView.setViewName("change-password");
		return modelAndView;
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ModelAndView changePasswordAction(@Valid ChangePassword changePassword, BindingResult bindingResult) throws MessagingException, IOException, TemplateException {
		ModelAndView modelAndView = new ModelAndView();
		UserDAO userExists = userService.findUserByEmail(changePassword.getEmail());

		if (userExists == null) {
			bindingResult
			.rejectValue("email", "error.changePassword",
					"There is not an user registered with the email provided");
		} else if(!bCryptPasswordEncoder.matches(changePassword.getOldPassword(), userExists.getPassword())) {
			bindingResult
			.rejectValue("oldPassword", "error.changePassword",
					"The old password is not correct");
		} else if(!bindingResult.hasErrors()) {
			emailService.sendChangePassword(userExists);
			userExists.setPassword(changePassword.getPassword());
			userService.updatePassword(userExists);

			modelAndView.addObject("successMessage", "Password reset successfully");
			modelAndView.addObject("changePassword", new ChangePassword());
		}

		modelAndView.setViewName("change-password");
		return modelAndView;
	}
}
