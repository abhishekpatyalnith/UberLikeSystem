package com.castle.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Rider {

	private Integer id;
	private String name;
	private String gender;
	private String age;
	private Location location;
	
	private Integer cabId;
}
