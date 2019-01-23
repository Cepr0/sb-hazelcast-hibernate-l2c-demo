package io.github.cepr0.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@EnableJpaRepositories("io.github.cepr0.demo.impl.repo")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

