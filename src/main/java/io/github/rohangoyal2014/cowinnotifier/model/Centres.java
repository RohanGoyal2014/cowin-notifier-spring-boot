package io.github.rohangoyal2014.cowinnotifier.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Setter;

@Setter
public class Centres {

	@JsonProperty("centers")
	private List<Centre> centres;

	@JsonProperty("centers")
	public List<Centre> getCentres() {
		return centres;
	}

	@Override
	public String toString() {
		return "Centres [centres=" + centres + "]";
	}

}
