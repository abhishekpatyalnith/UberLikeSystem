package com.castle.service;

import com.castle.dto.RideRequestDto;
import com.castle.dto.RideRequestResponse;
import com.castle.entity.Cab;
import com.castle.entity.Location;

public interface AllocationService {

	RideRequestResponse allocate(RideRequestDto rideRequest);
	Cab getNearestCab(Location location);
}
