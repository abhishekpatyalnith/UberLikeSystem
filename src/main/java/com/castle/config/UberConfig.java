package com.castle.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Configuration;

import com.castle.entity.Cab;

import lombok.Data;

@Configuration
@Data
public class UberConfig {

	
//	private List<Cab> cabs = new ArrayList<>();
	
	private Map<Integer, Cab> cabMap = new HashMap<>();
}
