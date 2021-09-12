package com.example.demo.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Run {

	private int i = 0;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(initialDelay = 0, fixedDelay = 300000)
	public void execute() {
		System.out.println("実行回数: " + ++i + ", 実行時間: " + sdf.format(new Date()));
	}

}