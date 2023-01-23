package com.example.demo.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable {
	static final long serialVersionUID = 1L;

	String id;
	String connectingId;
	String longitude;
	String latitude;
	String country;
	String datetime;
}
