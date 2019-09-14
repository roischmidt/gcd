package com.intercom.gcd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class, only handles Spring initializations. App main class is GCDController
 */
@SpringBootApplication
public class GcdApplication {

	public static void main(String[] args) {
		SpringApplication.run(GcdApplication.class, args);
	}
}
