package com.trilogyed.bookserviceeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BookServiceEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookServiceEurekaServerApplication.class, args);
	}

}
