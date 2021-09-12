package com.example.demo.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class LocationItem implements Serializable {

	private static final long serialVersionUID = 1L;

	public LocationItem(String name, String latitude, String longitude, String address, String telNumber,
			long datetime) {
		this.name=name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
		this.telNumber = telNumber;
		this.datetime = Long.toString(datetime);
	}

	String name;
	String latitude;
	String longitude;
	String address;
	String telNumber;
	String datetime;
}
