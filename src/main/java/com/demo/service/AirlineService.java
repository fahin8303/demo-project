package com.demo.service;

import com.demo.response.AirlineFare;
import com.demo.response.AirlineResponse;

public interface AirlineService {

	AirlineResponse getAllAirports();

	AirlineFare getFare(String origin, String destination);
}
