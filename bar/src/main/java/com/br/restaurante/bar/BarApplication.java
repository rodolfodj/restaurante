package com.br.restaurante.bar;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class BarApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarApplication.class, args);
	}

}
