package io.github.rohangoyal2014.cowinnotifier.utils;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.rohangoyal2014.cowinnotifier.model.AvailableCentre;

public class MessagingUtils {

	public static final Logger LOGGER = LoggerFactory.getLogger(MessagingUtils.class);
	
	public static String prevMsg = null;
	
	public static String buildMessageBody(List<AvailableCentre> centres) {
		LOGGER.info("Attempting to build message from availability list");
		
		StringBuilder message = new StringBuilder();
		message.append("AVAILABLE VACCINATION SLOTS\n");
		message.append("=================\n\n");
		
		for(AvailableCentre centre : centres) {
			message.append(centre.getName()).append("\n");
			message.append("[").append((centre.isPaid() ? "PAID" : "FREE")).append("]\n");
			message.append(centre.getFullAddress()).append("\n");
			message.append("Pincode: ").append(centre.getPinCode()).append("\n");
			message.append("Vaccine: ").append(centre.getVaccineName()).append("\n");
			message.append("Count Available: ").append(centre.getAvailableCapacity()).append("\n");
			message.append("Min Age Limit: ").append(centre.getAgeLimit()).append("\n");
			message.append("Date: ").append(centre.getDate()).append("\n");
			message.append("Timeslots: ").append(centre.getTimeSlots().toString()).append("\n");
			message.append("-------------------\n\n");
			
		}
		
		message.append("Login Here: ").append("https://selfregistration.cowin.gov.in/");
		
		String newMsg = message.toString();
		if(newMsg.equals(prevMsg)) {
			return null;
		}
		prevMsg = newMsg;
		return newMsg;
	}
	
}
