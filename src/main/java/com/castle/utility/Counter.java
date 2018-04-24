package com.castle.utility;

import lombok.Data;

@Data
public class Counter {

	static int instanceCounter = 0;

	public int counter = 0;

	public Counter() {
		instanceCounter++;
		counter = instanceCounter;
	}
}