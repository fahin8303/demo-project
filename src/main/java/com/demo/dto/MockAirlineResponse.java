package com.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MockAirlineResponse {
	
	@JsonProperty("_embedded")
	private Embedded embedded;

	private Page page;


}