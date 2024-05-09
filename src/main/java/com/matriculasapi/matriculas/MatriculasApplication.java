package com.matriculasapi.matriculas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MatriculasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatriculasApplication.class, args);
	}

}
