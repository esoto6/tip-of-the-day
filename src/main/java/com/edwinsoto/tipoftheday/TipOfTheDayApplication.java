package com.edwinsoto.tipoftheday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TipOfTheDayApplication {

	public static void main(String[] args) {
		SpringApplication.run(TipOfTheDayApplication.class, args);
	}

}
