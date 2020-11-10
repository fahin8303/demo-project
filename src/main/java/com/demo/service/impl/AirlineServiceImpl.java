package com.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.client.AirlineApiClient;
import com.demo.response.AirlineFare;
import com.demo.response.AirlineResponse;
import com.demo.responseconverter.AirlineConverter;
import com.demo.service.AirlineService;

@Component
public class AirlineServiceImpl implements AirlineService {

	@Autowired
	private AirlineApiClient airlineapiservice;

	@Autowired
	private AirlineConverter airlineresp;

	@Override
	public AirlineFare getFare(String origin, String destination) {
		return airlineapiservice.getFares(origin, destination);
	}

	@Override
	public AirlineResponse getAllAirports() {
		return airlineresp.listData(airlineapiservice.getlistOfAirports());
	}

}
