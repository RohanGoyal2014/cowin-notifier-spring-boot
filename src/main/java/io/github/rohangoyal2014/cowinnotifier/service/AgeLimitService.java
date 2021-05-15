package io.github.rohangoyal2014.cowinnotifier.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AgeLimitService {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(AgeLimitService.class);

	public static final Map<Integer, Integer> SUPPORTED_AGE_LIMITS = new ConcurrentHashMap<>();
	static {
		SUPPORTED_AGE_LIMITS.put(18, 1);
	}
	
	public List<Integer> getAgeLimits() {
		LOGGER.info("Returning ageLimits:" + SUPPORTED_AGE_LIMITS.keySet());
		return SUPPORTED_AGE_LIMITS.keySet().stream().collect(Collectors.toList());
	}
	
	public void addAgeLimit(int ageLimit) {
		if(SUPPORTED_AGE_LIMITS.containsKey(ageLimit)) {
			LOGGER.info("Age Limit [{}] already present", String.valueOf(ageLimit));
			return;
		}
		SUPPORTED_AGE_LIMITS.put(ageLimit, 1);
		LOGGER.info("Age Limit [{}] Added Successfully", String.valueOf(ageLimit));
	}
	
	public void removeAgeLimit(int ageLimit) {
		if(!SUPPORTED_AGE_LIMITS.containsKey(ageLimit)) {
			LOGGER.info("Age Limit [{}] does not exist", String.valueOf(ageLimit));
			return;
		}
		SUPPORTED_AGE_LIMITS.remove(ageLimit);
		LOGGER.info("Removed age limit:" + ageLimit);
	}
	
}
