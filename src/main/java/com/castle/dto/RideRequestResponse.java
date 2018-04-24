package com.castle.dto;

import com.castle.entity.Cab;
import com.castle.entity.Rider;

import lombok.Data;

@Data
public class RideRequestResponse {


	private Rider rider;
	private Cab cab;
	
	private String errorCode;
	private String status;
	private String message;
}
