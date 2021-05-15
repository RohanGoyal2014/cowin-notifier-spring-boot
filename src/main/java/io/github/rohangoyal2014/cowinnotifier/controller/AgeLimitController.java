package io.github.rohangoyal2014.cowinnotifier.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.rohangoyal2014.cowinnotifier.service.AgeLimitService;

@RestController
@RequestMapping("/age-limits")
public class AgeLimitController {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(AgeLimitController.class);
	
	private final AgeLimitService service;
	
	public AgeLimitController(AgeLimitService service) {
		this.service = service;
	}

	@GetMapping("/fetch")
	public List<Integer> getLimits() {
		LOGGER.info("Received request to get age limits");
		return service.getAgeLimits();
	}
	
	@PostMapping("/add")
	public void addAgeLimit(Integer ageLimit) {
		LOGGER.info("Received request to add age limit[{}] to set", String.valueOf(ageLimit));
		service.addAgeLimit(ageLimit);
	}
	
	@DeleteMapping("/remove")
	public void deleteAgeLimit(Integer ageLimit) {
		LOGGER.info("Received request to remove age limit[{}]", String.valueOf(ageLimit));
		service.removeAgeLimit(ageLimit);
	}
	
}
