package io.github.rohangoyal2014.cowinnotifier.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Setter;

@Setter
public class Session {

	@JsonProperty("date")
	private String date;

	@JsonProperty("available_capacity")
	private int availableCapacity;

	@JsonProperty("min_age_limit")
	private int minAgeLimit;

	@JsonProperty("vaccine")
	private String vaccineName;

	@JsonProperty("slots")
	private List<String> timeSlots;

	@JsonProperty("date")
	public String getDate() {
		return date;
	}

	@JsonProperty("available_capacity")
	public int getAvailableCapacity() {
		return availableCapacity;
	}

	@JsonProperty("min_age_limit")
	public int getMinAgeLimit() {
		return minAgeLimit;
	}

	@JsonProperty("vaccine")
	public String getVaccineName() {
		return vaccineName;
	}

	@JsonProperty("slots")
	public List<String> getTimeSlots() {
		return timeSlots;
	}

	@Override
	public String toString() {
		return "Session [date=" + date + ", availableCapacity=" + availableCapacity + ", minAgeLimit=" + minAgeLimit
				+ ", vaccineName=" + vaccineName + ", timeSlots=" + timeSlots + "]";
	}

}
