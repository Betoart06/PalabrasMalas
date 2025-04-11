package com.example.wordfilter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * Configuración de la aplicación.
 */
@Configuration
public class AppConfig {

    /**
     * Bean que define la lista de palabras prohibidas.
     * Es importante que esta lista coincida con la del frontend.
     */
    @Bean
    public List<String> prohibitedWords() {
        return Arrays.asList("huevon", "telita", "reta", "gorda", "pandita");
    }
}