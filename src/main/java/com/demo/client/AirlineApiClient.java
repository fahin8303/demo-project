package com.demo.client;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.demo.config.AccessConfig;
import com.demo.dto.MockAirlineResponse;
import com.demo.exception.AppException;
import com.demo.exception.Constants;
import com.demo.exception.ErrorCode;
import com.demo.response.AirlineFare;

import lombok.extern.slf4j.Slf4j;

/**
 * @author fahin.ansari
 *
 *         Rest API Call to another API to Receive Data
 */
@Component
@Slf4j
public class AirlineApiClient {

	@Autowired
	AccessConfig accessconfig;

	@Autowired
	@Qualifier("oAuth2Rest")
	private RestTemplate oAuth2RestTemplate;

	public MockAirlineResponse getlistOfAirports() {
		log.info("getlistOfAirports:Start");
		ResponseEntity<MockAirlineResponse> response = null;
		try {

			response = oAuth2RestTemplate.exchange(accessconfig.getAirportsUrl(), HttpMethod.GET, null,
					MockAirlineResponse.class);
		} catch (ResourceAccessException | HttpStatusCodeException exception) {
			handleException(response.getStatusCode().value());
		}
		log.info("getlistOfAirports:End");
		return response.getBody();
	}

	public AirlineFare getFares(String origin, String destination) {
		log.info("getFares:Start");
		ResponseEntity<AirlineFare> response = null;
		Map<String, String> uriVariables = null;
		try {

			uriVariables = new HashMap<>();
			uriVariables.put(Constants.ORIGIN_PATH_PARAM, origin);
			uriVariables.put(Constants.DEST_PATH_PARAM, destination);

			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(accessconfig.getFareUrl());

			response = oAuth2RestTemplate.exchange(builder.buildAndExpand(uriVariables).toUri().toString(),
					HttpMethod.GET, null, AirlineFare.class);
		} catch (ResourceAccessException | HttpStatusCodeException exception) {
			handleException(response.getStatusCode().value());
		}
		log.info("getFares:End");
		return response.getBody();
	}

	public void handleException(int httpCode) {
		switch (httpCode) {
		case 401:
			throw new AppException(ErrorCode.UNAUTHORIZED);
		case 400:
			throw new AppException(ErrorCode.VALIDATION_OR_PARSING_ERROR);
		case 403:
			throw new AppException(ErrorCode.FORBIDDEN);
		default:
			throw new AppException(ErrorCode.APPLICATION_ERROR);
		}

	}

}
