package com.demo.responseconverter;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.demo.dto.Locations;
import com.demo.dto.MockAirlineResponse;
import com.demo.exception.AppException;
import com.demo.exception.ErrorCode;
import com.demo.response.AirlineModel;
import com.demo.response.AirlineResponse;

@Component
public class AirlineConverter {

	public AirlineResponse listData(MockAirlineResponse data) {
		AirlineResponse response = new AirlineResponse();

		if (!data.getEmbedded().getLocations().isEmpty()) {
			response.setList(data.getEmbedded().getLocations().stream()
					                       .map(this::createAirlineModel)
					                       .collect(Collectors.toList()));
		} else {
			throw new AppException(ErrorCode.VALIDATION_OR_PARSING_ERROR);
		}
		return response;
	}

	private AirlineModel createAirlineModel(Locations location) {
		return AirlineModel.builder()
				.code(location.getCode())
				.desc(location.getDescription())
				.build();
	}
}
