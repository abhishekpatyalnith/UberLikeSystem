package com.castle.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.castle.config.UberConfig;
import com.castle.entity.Location;
import com.castle.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {
	
	private static final Logger logger = LoggerFactory.getLogger(LocationServiceImpl.class);
	
	@Autowired
	private UberConfig uberConfig;
	
	@Value("${ride_request_radius}")
	private String radius;

	@Override
	public Integer getDistance(Location a, Location b) {

		Integer distanceSquare = ((a.getX() - b.getX()) * (a.getX() - b.getX()))
				+ ((a.getY() - b.getY()) * (a.getY() - b.getY()));

		return distanceSquare;
	}
}
