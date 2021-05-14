package io.github.rohangoyal2014.cowinnotifier.model;

import java.io.Serializable;

public class Location implements Serializable {

	private static final long serialVersionUID = -1732837035376979211L;

	private String districtName;
	private String districtId;

	public Location(String districtName, String districtId) {
		this.districtName = districtName;
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	@Override
	public String toString() {
		return "LocationModel [districtName=" + districtName + ", districtId=" + districtId + "]";
	}

}
