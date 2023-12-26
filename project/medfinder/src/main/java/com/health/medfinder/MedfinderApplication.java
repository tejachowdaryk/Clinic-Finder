package com.health.medfinder;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class MedfinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedfinderApplication.class, args);
	}

}
