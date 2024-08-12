package com.wissen.initializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*")
public class WissenInitializerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WissenInitializerApplication.class, args);
	}

}