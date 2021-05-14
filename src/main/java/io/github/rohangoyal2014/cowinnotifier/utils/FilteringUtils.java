package io.github.rohangoyal2014.cowinnotifier.utils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import io.github.rohangoyal2014.cowinnotifier.commons.DataFeedConstants;
import io.github.rohangoyal2014.cowinnotifier.model.AvailableCentre;
import io.github.rohangoyal2014.cowinnotifier.model.Centre;
import io.github.rohangoyal2014.cowinnotifier.model.Centres;
import io.github.rohangoyal2014.cowinnotifier.model.Session;

public class FilteringUtils {

	public static List<AvailableCentre> getAvailableCentres(Centres centres) {
		List<AvailableCentre> availableCentres = new ArrayList<AvailableCentre>();
		
		for(Centre centre : centres.getCentres()) {
			String centreAddress = MessageFormat.format("{0}\n{1}\n{2}", 
														centre.getAddress(), 
														centre.getDistrict(), 
														centre.getState());
			for(Session session : centre.getSessions()) {
				if(!DataFeedConstants.SUPPORTED_AGE_LIMITS.contains(session.getMinAgeLimit()) 
						|| session.getAvailableCapacity() == 0) {
					continue;
				}
				
				AvailableCentre availableCentre = new AvailableCentre();
				
				availableCentre.setAvailableCapacity(session.getAvailableCapacity());
				availableCentre.setDate(session.getDate());
				availableCentre.setFullAddress(centreAddress);
				availableCentre.setName(centre.getCenterName());
				availableCentre.setPaid(DataFeedConstants.PAID_KEY.equals(centre.getFeeType()));
				availableCentre.setPinCode(centre.getPincode());
				availableCentre.setTimeSlots(session.getTimeSlots());
				availableCentre.setVaccineName(session.getVaccineName());
				
				availableCentres.add(availableCentre);
			}
		}
		return availableCentres;
	}
	
}
