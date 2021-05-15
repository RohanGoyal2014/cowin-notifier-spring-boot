package io.github.rohangoyal2014.cowinnotifier.service;

import java.util.List;

import io.github.rohangoyal2014.cowinnotifier.model.AvailableCentre;

public interface INotificationService {
	
	public void send(List<AvailableCentre> centres);
	
}
