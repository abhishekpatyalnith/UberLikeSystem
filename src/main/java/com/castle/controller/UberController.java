package com.castle.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.castle.dto.FinishRideRequest;
import com.castle.dto.FinishRideResponse;
import com.castle.dto.RideRequestDto;
import com.castle.dto.RideRequestResponse;
import com.castle.entity.Cab;
import com.castle.service.AllocationService;
import com.castle.service.CabService;

@RestController
@RequestMapping(value = { "/uber" })
public class UberController {

	private static final Logger logger = LoggerFactory.getLogger(UberController.class);

	@Autowired
	private AllocationService allocationService;

	@Autowired
	private CabService cabService;

	@RequestMapping(value = "/allocateRide", method = RequestMethod.POST)
	public ResponseEntity<RideRequestResponse> allocateNewIpAddress(@RequestBody RideRequestDto rideRequest) {

		logger.info("allocating ride");
		RideRequestResponse rideRequestResponse = allocationService.allocate(rideRequest);

		return new ResponseEntity<>(rideRequestResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/launchCab", method = RequestMethod.POST)
	public ResponseEntity<Boolean> launchCab(@RequestBody Cab cabRequest) {

		logger.info("launching a new cab");
		Boolean cabLaunchResponse = cabService.launchCab(cabRequest);

		return new ResponseEntity<>(cabLaunchResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/finishRide", method = RequestMethod.POST)
	public ResponseEntity<FinishRideResponse> finishRide(@RequestBody FinishRideRequest finishRideRequest) {

		logger.info("launching a new cab");
		FinishRideResponse finishRideResponse = cabService.finishRide(finishRideRequest);

		return new ResponseEntity<>(finishRideResponse, HttpStatus.OK);
	}
}
