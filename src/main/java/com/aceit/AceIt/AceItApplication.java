package com.aceit.AceIt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.aceit.controller", "com.aceit.model", "com.aceit.validator", "com.aceit.DAO", "com.aceit.util"})
public class AceItApplication {

	public static void main(String[] args) {
		SpringApplication.run(AceItApplication.class, args);
	}

}
