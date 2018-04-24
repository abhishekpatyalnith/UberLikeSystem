package com.castle.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.castle.config.UberConfig;
import com.castle.constants.ClientResponseStatus;
import com.castle.constants.ErrorCodes;
import com.castle.dto.RideRequestDto;
import com.castle.dto.RideRequestResponse;
import com.castle.entity.Cab;
import com.castle.entity.Location;
import com.castle.entity.Ride;
import com.castle.entity.Rider;
import com.castle.service.AllocationService;
import com.castle.service.LocationService;
import com.castle.service.RiderService;

@Service
public class AllocationServiceImpl implements AllocationService {

	private static final Logger logger = LoggerFactory.getLogger(AllocationServiceImpl.class);

	@Autowired
	private UberConfig uberConfig;

	@Autowired
	private LocationService locationService;

	@Value("${ride_request_radius}")
	private String radius;

	@Autowired
	private RiderService riderService;

	@Override
	public RideRequestResponse allocate(RideRequestDto rideRequest) {

		RideRequestResponse rideRequestResponse = new RideRequestResponse();
		logger.info("checking if any cab is available");

		try {
			Cab cab = getNearestCab(rideRequest.getLocation());
			if (cab == null) {
				rideRequestResponse.setErrorCode(ErrorCodes.NO_CAB_AVAILABLE);
				rideRequestResponse.setStatus(ClientResponseStatus.FAILURE);
				return rideRequestResponse;
			}
			logger.info("voila cab is available");
			Rider rider = rideRequest.getRider();
			Location location = rideRequest.getLocation();
			rider = riderService.createCustomer(rider);
			Ride ride = new Ride();
			ride.setStartTime(new Date());
			cab.setRide(ride);
			cab = configureCabAndRider(cab, rider, location);
			rideRequestResponse.setMessage("Ride started at " + ride.getStartTime());
			rideRequestResponse.setCab(cab);
			rideRequestResponse.setRider(rideRequest.getRider());
			rideRequestResponse.setStatus(ClientResponseStatus.SUCCESS);
			rideRequestResponse.setErrorCode(ErrorCodes.CAB_ALLOCATED_SUCCESS);
		} catch (Exception e) {
			logger.info("exception occurred ", e);
			rideRequestResponse.setRider(rideRequest.getRider());
			rideRequestResponse.setStatus(ClientResponseStatus.ERROR);
			rideRequestResponse.setErrorCode(ErrorCodes.CAB_REQUEST_NOT_COMPLETED);
		}

		return rideRequestResponse;
	}

	public Cab configureCabAndRider(Cab c, Rider rider,Location location) {
		c.setActive(true);
		c.setFree(false);
		c.setRiderId(rider.getId());
		c.setLocation(location);

		rider.setCabId(c.getId());
		return c;
	}

	@Override
	public Cab getNearestCab(Location location) {

		Map<Integer, Cab> cabMap = uberConfig.getCabMap();

		Cab cab = null;
		int minDistance = Integer.MAX_VALUE;

		for (Entry<Integer, Cab> c : cabMap.entrySet()) {
			Cab current = c.getValue();
			boolean isActive = current.isActive();

			if (isActive && current.isFree()) {
				int distance = locationService.getDistance(location, current.getLocation());
				logger.info("distance " + distance);
				if (distance > Integer.valueOf(radius)) {
					continue;
				} else {
					if (distance < minDistance) {
						cab = current;
						minDistance = distance;
					}
				}

			}
		}
		return cab;
	}

}
