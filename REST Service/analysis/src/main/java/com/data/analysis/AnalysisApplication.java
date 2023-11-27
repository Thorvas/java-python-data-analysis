package com.data.analysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.data.analysis")
public class AnalysisApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnalysisApplication.class, args);
	}

}
