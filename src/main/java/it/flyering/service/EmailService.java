package it.flyering.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import it.flyering.dao.UserDAO;
import it.flyering.model.Mail;
import it.flyering.model.User;
import it.flyering.utils.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private FreeMarkerConfigurationFactory freeMarkerConfigurationFactory;

	public void sendAdminRegistrationCompletedEmail(User user) throws MessagingException, IOException, TemplateException {
		Mail mail = new Mail();
		mail.setFrom("noreply@liberty-it.co.uk");
		mail.setTo(user.getEmail());
		mail.setSubject("Welcome " + user.getName());

		Map<String, Object> model = new HashMap<>();
		model.put("name", user.getName());
		mail.setModel(model);
		
		sendSimpleMessage(mail, "client-email-template.ftl");
	}
	
	public void sendUserRegistrationCompletedEmail(User user) throws MessagingException, IOException, TemplateException {
		Mail mail = new Mail();
		mail.setFrom("noreply@liberty-it.co.uk");
		mail.setTo(user.getEmail());
		mail.setSubject("Welcome " + user.getName());

		Map<String, Object> model = new HashMap<>();
		model.put("name", user.getName());
		mail.setModel(model);
		
		sendSimpleMessage(mail, "client-email-template.ftl");
	}
	
	public void sendResetPassword(UserDAO user) throws MessagingException, IOException, TemplateException {
		Mail mail = new Mail();
		mail.setFrom("noreply@liberty-it.co.uk");
		mail.setTo(user.getEmail());
		mail.setSubject("Reset Password");

		Map<String, Object> model = new HashMap<>();
		model.put("name", user.getName());
		model.put("password", user.getPassword());
		mail.setModel(model);
		
		sendSimpleMessage(mail, "reset-password-email-template.ftl");
	}

	private void sendSimpleMessage(final Mail mail, final String template) throws MessagingException, IOException,
	TemplateException {

		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,
				MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());

		Configuration freemarkerConfig = freeMarkerConfigurationFactory.createConfiguration();
		freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/");
		Template t = freemarkerConfig.getTemplate(Constants.EMAIL_TEMPLATES_PATH + template);

		String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, mail.getModel());

		helper.setTo(mail.getTo());
		helper.setText(html, true);
		helper.setSubject(mail.getSubject());
		helper.setFrom(mail.getFrom());

		emailSender.send(message);
	}
}
