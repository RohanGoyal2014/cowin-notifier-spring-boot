package io.github.rohangoyal2014.cowinnotifier.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.github.rohangoyal2014.cowinnotifier.model.Location;

@Service
public class LocationService {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(LocationService.class);

	public static final Map<String, Location> coveredLocations = new ConcurrentHashMap<>();
	
	public List<Location> getCoveredLocations() {
		LOGGER.info("Returning covered locations:" + coveredLocations);
		return coveredLocations.values().stream().collect(Collectors.toList());
	}
	
	public void addLocation(Location location) {
		if(coveredLocations.containsKey(location.getDistrictId())) {
			LOGGER.info("Location [{}] already present", location.toString());
			return;
		}
		coveredLocations.put(location.getDistrictId(), location);
		LOGGER.info("Location [{}] Added Successfully", location.toString());
	}
	
	public void removeLocation(String id) {
		if(!coveredLocations.containsKey(id)) {
			LOGGER.info("Location [{}] does not exist", id);
			return;
		}
		Location location = coveredLocations.get(id);
		coveredLocations.remove(id);
		LOGGER.info("Removed location:" + location);
	}
	
}
