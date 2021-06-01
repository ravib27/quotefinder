package com.zopa.quotefinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackageClasses = QuotefinderController.class)
public class QuotefinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuotefinderApplication.class, args);
	}

}
