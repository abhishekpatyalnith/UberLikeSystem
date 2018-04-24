package com.castle.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.castle.config.UberConfig;
import com.castle.constants.ClientResponseStatus;
import com.castle.dto.FinishRideRequest;
import com.castle.dto.FinishRideResponse;
import com.castle.entity.Cab;
import com.castle.entity.Location;
import com.castle.service.CabService;
import com.castle.service.LocationService;
import com.castle.utility.Counter;

@Service
public class CabServiceImpl implements CabService {

	private static final Logger logger = LoggerFactory.getLogger(CabServiceImpl.class);

	@Autowired
	private UberConfig uberConfig;
	
	@Value("${ride_base_fare}")
	private String baseFare;
	
	@Value("${ride_fare_time_exponent}")
	private String timeExponent;
	
	@Value("${ride_fare_distance_exponent}")
	private String distanceExponent;
	
	@Autowired
	private LocationService locationService;

	@Override
	public boolean launchCab(Cab cab) {

		Counter counter = new Counter();
		int assignedId = counter.counter;
		cab.setId(assignedId);
		cab.setActive(true);
		cab.setFree(true);
		boolean isCabLaunched = false;
		try {
			uberConfig.getCabMap().put(assignedId, cab);
			isCabLaunched = true;
		} catch (Exception e) {
			logger.error("exception occcurred ", e);
		}
		return isCabLaunched;
	}

	@Override
	public FinishRideResponse finishRide(FinishRideRequest finishRideRequest) {

		Integer cabId = finishRideRequest.getCabId();
		FinishRideResponse finishRideResponse = new FinishRideResponse();

		Cab cab = uberConfig.getCabMap().get(cabId);
		if (cab == null) {
			logger.info("invalid finish ride request");
			finishRideResponse.setStatus(ClientResponseStatus.FAILURE);
		} else {
			logger.info("finishing ride for cab with id " + cabId);
			Date endTime = new Date();
			cab = calculateFare(cab,finishRideRequest,endTime);
			cab.setFree(true);
			cab.setRiderId(null);
			cab.setLocation(finishRideRequest.getLocation());
			uberConfig.getCabMap().put(cabId, cab);
			finishRideResponse.setCab(cab);
			
			finishRideResponse.setStatus(ClientResponseStatus.SUCCESS);
		}

		return finishRideResponse;
	}
	public Cab calculateFare(Cab cab,FinishRideRequest finishRideRequest, Date endTime){
		logger.info("calculating fare");
		Double fare = Double.valueOf(baseFare);
		logger.info("base fare "+fare);
		
		Location startLocation = cab.getLocation();
		Location endLocation = finishRideRequest.getLocation();
		
		Double distance = Math.sqrt(locationService.getDistance(startLocation, endLocation));
		Date startTime = cab.getRide().getStartTime();
		int duration = getDuration(startTime, endTime);
		logger.info("ride duration "+duration);
		
		fare += (Integer.valueOf(distanceExponent)*distance) + (Integer.valueOf(timeExponent)*duration);
		
		cab.getRide().setDuration(String.valueOf(duration));
		cab.getRide().setEndTime(endTime);
		cab.getRide().setFare(String.valueOf(fare));
		
		return cab;
	}
	
	private Integer getDuration(Date startTime, Date endTime){
		
		Integer seconds = Integer.valueOf(String.valueOf((endTime.getTime()-startTime.getTime())/1000));
		return seconds;
	}

}
