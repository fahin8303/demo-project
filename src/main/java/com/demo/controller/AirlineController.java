package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.Response;
import com.demo.response.AirlineFare;
import com.demo.response.AirlineResponse;
import com.demo.service.AirlineService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AirlineController {

	@Autowired
	private AirlineService airlineService;

	@GetMapping(value = "airports")
	public ResponseEntity<Response> getAllAirports() {
		AirlineResponse data = airlineService.getAllAirports();
		return new ResponseEntity<>(Response.getSuccessResponse(data.getList()), HttpStatus.OK);
	}

	@GetMapping(value = "getfare/{origin}/{destination}")
	public ResponseEntity<Response> getFare(@PathVariable String origin, @PathVariable String destination) {
		AirlineFare data = airlineService.getFare(origin, destination);
		return new ResponseEntity<>(Response.getSuccessResponse(data), HttpStatus.OK);
	}
}
