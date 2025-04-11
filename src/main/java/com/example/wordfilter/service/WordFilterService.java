package com.example.wordfilter.service;

import com.example.wordfilter.model.Message;

/**
 * Interfaz del servicio de filtrado de palabras.
 */
public interface WordFilterService {

    /**
     * Filtra un mensaje, sustituyendo las palabras prohibidas.
     *
     * @param message El mensaje a filtrar
     * @return El mensaje filtrado
     */
    Message filterMessage(Message message);
}