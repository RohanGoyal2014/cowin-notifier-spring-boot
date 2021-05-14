package io.github.rohangoyal2014.cowinnotifier.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.github.rohangoyal2014.cowinnotifier.model.AvailableCentre;

@Service("whatsapp-service")
public class WhatsAppNotificationService implements INotificationService {

	public static final Logger LOGGER = LoggerFactory.getLogger(WhatsAppNotificationService.class);
	
	@Override
	public void send(List<AvailableCentre> centres) {
		if(centres.isEmpty()) {
			LOGGER.info("No availables centres found on provided districts!");
			return;
		}
		LOGGER.info("Found available centres:" + centres);
	}

	
	
}
