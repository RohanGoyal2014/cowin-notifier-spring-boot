package io.github.rohangoyal2014.cowinnotifier.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.rohangoyal2014.cowinnotifier.commons.LocationConstants;
import io.github.rohangoyal2014.cowinnotifier.model.AvailableCentre;
import io.github.rohangoyal2014.cowinnotifier.model.Centres;
import io.github.rohangoyal2014.cowinnotifier.utils.FilteringUtils;
import io.github.rohangoyal2014.cowinnotifier.utils.HttpUtils;

@Service
@EnableScheduling
public class DataFeedService {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(DataFeedService.class);

	private final String url;
	private final  String districtIdParam;
	private final String dateParam;
	
	private final INotificationService notificationService;
	private final AlertService alertService;

	public DataFeedService(@Value("${cowin.url}") String url,
							   @Value("${cowin.url.param.district-id-param}") String districtIdParam,
							   @Value("${cowin.url.param.date-param}") String dateParam,
							   @Qualifier("telegram-service") INotificationService notificationService,
							   AlertService alertService) {
		this.url = url;
		this.districtIdParam = districtIdParam;
		this.dateParam = dateParam;
		this.notificationService = notificationService;
		this.alertService = alertService;
	}

	@Scheduled(fixedDelayString = "${API_HIT_DELAY}")
	public void fetchData() {
		LOGGER.info("Running Schedule for fetching vaccine data");
		
		if(LocationService.coveredLocations.isEmpty()) {
			LOGGER.info("No districts are available, hence skipping...");
			return;
		}

		LocalDate date = LocalDate.now(ZoneId.of(LocationConstants.TIMEZONE_INDIA));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		Map<String, String> headers = new HashMap<>();
		headers.put("User-Agent", "Mozilla/5.0 (X11; Linux i686) AppleWebKit/5311 (KHTML, like Gecko) Chrome/38.0.810.0 Mobile Safari/5311");
		headers.put("Accept-Language", "en_IN");
		
		List<AvailableCentre> validCentres = new ArrayList<>();
		
		try {
			for(String district: LocationService.coveredLocations.keySet()) {
				Map<String, String> params = new HashMap<>();
				params.put(districtIdParam, district);
				
				while(true) {
					params.put(dateParam, date.format(formatter));
					// Moving to next week
					date = date.plusDays(7);
					
					Pair<Integer, String> response = HttpUtils.performGet(url, params, headers);
					
					if(response.getLeft() != 200) {
						LOGGER.info("Unexpected Response from API:" + response.getLeft() + "content is: {}", response.getRight());
						break;
					}
					
					Centres centres = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
														.readValue(response.getRight(), Centres.class);
					if(centres.getCentres() == null || centres.getCentres().isEmpty()) {
						// No more slots available
						break;
					}
					
					validCentres.addAll(FilteringUtils.getAvailableCentres(centres));
				}
			}
			notificationService.send(validCentres);
		} catch (Exception e) {
			LOGGER.error("Error:" + e, e);
			alertService.alert("Error: " + e);
		}
	}

}
