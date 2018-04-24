package com.castle.dto;

import com.castle.entity.Location;

import lombok.Data;

@Data
public class FinishRideRequest {

	private Location location;
	private Integer cabId;
}
