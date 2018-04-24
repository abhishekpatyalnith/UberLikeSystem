package com.castle.dto;

import com.castle.entity.Cab;

import lombok.Data;

@Data
public class FinishRideResponse {

	String status;
	Cab cab;
}
