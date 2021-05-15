package io.github.rohangoyal2014.cowinnotifier.model;

import java.util.List;

public class AvailableCentre {

	private String name;
	private String fullAddress;
	private String pinCode;
	private boolean isPaid;
	private int availableCapacity;
	private String vaccineName;
	private String date;
	private int ageLimit;
	private List<String> timeSlots;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public int getAvailableCapacity() {
		return availableCapacity;
	}

	public void setAvailableCapacity(int availableCapacity) {
		this.availableCapacity = availableCapacity;
	}

	public String getVaccineName() {
		return vaccineName;
	}

	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<String> getTimeSlots() {
		return timeSlots;
	}

	public void setTimeSlots(List<String> timeSlots) {
		this.timeSlots = timeSlots;
	}

	public int getAgeLimit() {
		return ageLimit;
	}

	public void setAgeLimit(int ageLimit) {
		this.ageLimit = ageLimit;
	}

	@Override
	public String toString() {
		return "AvailableCentre [name=" + name + ", fullAddress=" + fullAddress + ", pinCode=" + pinCode + ", isPaid="
				+ isPaid + ", availableCapacity=" + availableCapacity + ", vaccineName=" + vaccineName + ", date="
				+ date + ", ageLimit=" + ageLimit + ", timeSlots=" + timeSlots + "]";
	}

}
