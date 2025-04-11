package com.example.wordfilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Clase principal de la aplicación.
 * Habilita el procesamiento de aspectos con @EnableAspectJAutoProxy
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class WordFilterApplication {
    public static void main(String[] args) {
        SpringApplication.run(WordFilterApplication.class, args);
    }
}