package com.ccsw.tutorialeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TutorialEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TutorialEurekaApplication.class, args);
	}

}
