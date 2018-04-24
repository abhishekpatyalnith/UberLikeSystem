package com.castle.dto;

import com.castle.entity.Location;
import com.castle.entity.Rider;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RideRequestDto {

	private Rider rider;
	private Location location;
}
