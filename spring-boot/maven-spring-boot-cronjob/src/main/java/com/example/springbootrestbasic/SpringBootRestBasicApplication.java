package com.example.springbootrestbasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //necesario para ejecutar el cron job
public class SpringBootRestBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestBasicApplication.class, args);
	}

}
