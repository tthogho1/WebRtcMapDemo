package com.example.demo.task;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.example.demo.bean.LocationItem;
import com.example.demo.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RunImportCache {
	@Autowired
	ItemService itemService;

	private static String LOAD_FILE_PATH = "/temp/test.csv";
	private static String BKUP_FILE_PATH = "/temp/test.csv";

	@Scheduled(initialDelay = 0, fixedDelay = 30000)
	public void execute() {


		log.info("task start: " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		Path path = Paths.get(LOAD_FILE_PATH);
		if (!path.toFile().exists()) {
			log.info("file nof found");
			return;
		}

		List<String> lines = null;
		try {
			lines = Files.readAllLines(path, StandardCharsets.UTF_8);
		} catch (Exception e) {
			e.printStackTrace();
		}

		lines.stream().forEach(line -> {
			String[] infos = line.split(",");

			String lat = infos[3].split("/")[4];
			String lng = infos[4];

			LocationItem item = new LocationItem(infos[0], lat, lng, infos[7], infos[8], System.currentTimeMillis());

			itemService.createItem(item);
		});

		File bkup = new File(BKUP_FILE_PATH + System.currentTimeMillis() );
        path.toFile().renameTo(bkup);

		log.info("task complete: " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	}

}