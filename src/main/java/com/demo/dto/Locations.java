package com.demo.dto;

import lombok.Data;

@Data
public class Locations {
	private String code;

	private String name;

	private String description;

	private Coordinates coordinates;

	private Parent parent;

	

}
