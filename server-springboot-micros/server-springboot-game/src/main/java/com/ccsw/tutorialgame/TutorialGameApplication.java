package com.ccsw.tutorialgame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TutorialGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(TutorialGameApplication.class, args);
	}

}
