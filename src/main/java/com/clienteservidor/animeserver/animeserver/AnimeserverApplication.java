package com.clienteservidor.animeserver.animeserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AnimeserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnimeserverApplication.class, args);
	}

}
