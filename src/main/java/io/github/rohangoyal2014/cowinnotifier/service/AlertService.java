package io.github.rohangoyal2014.cowinnotifier.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class AlertService {

	public static final Logger LOGGER = LoggerFactory.getLogger(AlertService.class);

	private JavaMailSender mailSender;

	public AlertService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void alert(String messageBody) {
		LOGGER.info("Attempting to send mail");

		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("noreply@rohangoyal2014.com");
			message.setTo("rohan.me2014@gmail.com");
			message.setSubject("Cowin Bot App Exception");
			message.setText(messageBody);
			mailSender.send(message);
			LOGGER.info("Mail Sent Successfully!");
		} catch (Exception e) {
			LOGGER.error("Error while sending mail:" + e, e);
		}
	}

}
