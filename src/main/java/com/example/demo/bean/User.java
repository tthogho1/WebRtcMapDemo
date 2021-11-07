package com.example.demo.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable {
	String id;
	String connectingId;
	String longitude;
	String latitude;
	String datetime;
}
