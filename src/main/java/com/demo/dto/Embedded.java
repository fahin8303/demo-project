package com.demo.dto;

import java.util.List;

import lombok.Data;

@Data
public class Embedded {
	private List<Locations> locations;
}