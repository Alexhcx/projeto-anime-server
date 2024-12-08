package com.clienteservidor.animeserver.animeserver;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.clienteservidor.animeserver.animeserver.view.JavaFxApplication;

import javafx.application.Application;

@ComponentScan(basePackages = "com.clienteservidor.*")
@SpringBootApplication
@EnableJpaAuditing
public class AnimeserverApplication {
    public static void main(String[] args) {
        Application.launch(JavaFxApplication.class, args);
    }
}