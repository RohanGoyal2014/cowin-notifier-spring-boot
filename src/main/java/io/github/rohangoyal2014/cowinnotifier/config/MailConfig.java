package io.github.rohangoyal2014.cowinnotifier.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
	
	private final String senderEmail;
	private final String senderPassword;
	
	public MailConfig(@Value("${DEV_EMAIL}") String senderEmail, 
					  @Value("${DEV_PASSWORD}") String senderPassword) {
		this.senderEmail = senderEmail;
		this.senderPassword = senderPassword;
	}

	@Bean("mail-bean")
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		
		mailSender.setUsername(senderEmail);
		mailSender.setPassword(senderPassword);

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		return mailSender;
	}

}
