package com.hhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaRepositories("com.hhub.repo") 
@EntityScan("com.hhub.model")
@SpringBootApplication
@EnableScheduling
public class CrudCmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudCmsApplication.class, args);
	}
}
