package com.edwinsoto.tipoftheday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class TipofthedayApplication {

	public static void main(String[] args) {
		SpringApplication.run(TipofthedayApplication.class, args);
	}

}
