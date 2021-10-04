package com.br.restaurante.kitchen;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class KitchenApplication {

	public static void main(String[] args) {
		SpringApplication.run(KitchenApplication.class, args);
	}

}