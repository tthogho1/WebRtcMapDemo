package com.example.demo.bean;

import java.io.Serializable;

import lombok.Data;
import com.example.demo.bean.Status;

@Data
public class ResultStatus implements Serializable {
	String status = Status.OK.toString();
}
