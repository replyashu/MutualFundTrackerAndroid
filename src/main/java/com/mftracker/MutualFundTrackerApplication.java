package com.mftracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class MutualFundTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MutualFundTrackerApplication.class, args);
	}

}
