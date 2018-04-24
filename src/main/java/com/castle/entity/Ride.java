package com.castle.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Ride {

	private Date startTime;
	private Date endTime;
	public String duration;
	private String fare;
}
