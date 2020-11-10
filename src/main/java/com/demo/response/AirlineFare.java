package com.demo.response;

import lombok.Data;

@Data
public class AirlineFare {
	double amount;
	String currency;
	String origin, destination;

}
