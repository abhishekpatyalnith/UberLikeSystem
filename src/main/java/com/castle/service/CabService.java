package com.castle.service;

import com.castle.dto.FinishRideRequest;
import com.castle.dto.FinishRideResponse;
import com.castle.entity.Cab;

public interface CabService {

	boolean launchCab(Cab cab);
	FinishRideResponse finishRide(FinishRideRequest finishRideRequest);
}
