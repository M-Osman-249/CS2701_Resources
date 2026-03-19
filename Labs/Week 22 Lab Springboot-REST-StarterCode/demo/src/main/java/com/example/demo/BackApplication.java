package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BackApplication {
	private static final Logger logger = LoggerFactory.getLogger(BackApplication.class);

	public static void main(String[] args) {
		logger.info("Your message here"); // instead of System.out.println
		SpringApplication.run(BackApplication.class, args);
	}
}