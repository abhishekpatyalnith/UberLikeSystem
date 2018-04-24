package com.castle.service.impl;

import org.springframework.stereotype.Service;

import com.castle.entity.Rider;
import com.castle.service.RiderService;
import com.castle.utility.Counter;

@Service
public class RiderServiceImpl implements RiderService {

	@Override
	public Rider createCustomer(Rider rider) {
		Counter counter = new Counter();
		int assignedId = counter.counter;
		rider.setId(assignedId);
		return rider;

	}

}
