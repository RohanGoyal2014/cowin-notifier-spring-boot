package io.github.rohangoyal2014.cowinnotifier.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.rohangoyal2014.cowinnotifier.model.Location;
import io.github.rohangoyal2014.cowinnotifier.service.LocationService;

@RestController
@RequestMapping("/locations")
public class LocationController {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(LocationController.class);

	private final LocationService service;
	
	public LocationController(LocationService service) {
		this.service = service;
	}
	
	@GetMapping("/fetch")
	public List<Location> getCoveredLocations() {
		LOGGER.info("Received request to get covered locations");
		return service.getCoveredLocations();
	}
	
	@PostMapping("/add")
	public void addLocation(String id, String locationName) {
		LOGGER.info("Received request to add location [{}:{}]", id, locationName);
		service.addLocation(new Location(locationName, id));
	}
	
	@DeleteMapping
	public void deleteLocation(String id) {
		LOGGER.info("Received request to remove location with id:" + id);
		service.removeLocation(id);
	}
	
}
