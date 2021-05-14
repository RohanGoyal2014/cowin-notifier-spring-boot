package io.github.rohangoyal2014.cowinnotifier.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Setter;

@Setter
public class Centre {

	@JsonProperty("name")
	private String centerName;

	@JsonProperty("address")
	private String address;

	@JsonProperty("state_name")
	private String state;

	@JsonProperty("district_name")
	private String district;

	@JsonProperty("pincode")
	private String pincode;

	@JsonProperty("fee_type")
	private String feeType;

	@JsonProperty("sessions")
	private List<Session> sessions;

	@JsonProperty("name")
	public String getCenterName() {
		return centerName;
	}

	@JsonProperty("address")
	public String getAddress() {
		return address;
	}

	@JsonProperty("state_name")
	public String getState() {
		return state;
	}

	@JsonProperty("district_name")
	public String getDistrict() {
		return district;
	}

	@JsonProperty("pincode")
	public String getPincode() {
		return pincode;
	}

	@JsonProperty("fee_type")
	public String getFeeType() {
		return feeType;
	}

	@JsonProperty("sessions")
	public List<Session> getSessions() {
		return sessions;
	}

	@Override
	public String toString() {
		return "Centre [centerName=" + centerName + ", address=" + address + ", state=" + state + ", district="
				+ district + ", pincode=" + pincode + ", feeType=" + feeType + ", sessions=" + sessions + "]";
	}

}
