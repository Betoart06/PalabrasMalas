package com.example.wordfilter.service;

import com.example.wordfilter.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio de filtrado de palabras.
 * Este servicio se registra como un bean en el contexto de Spring.
 */
@Service
public class WordFilterServiceImpl implements WordFilterService {

    private final List<String> prohibitedWords;

    @Autowired
    public WordFilterServiceImpl(List<String> prohibitedWords) {
        this.prohibitedWords = prohibitedWords;
    }

    /**
     * Este método es interceptado por el aspecto WordFilterAspect.
     * La lógica real de filtrado se implementa en el aspecto.
     */
    @Override
    public Message filterMessage(Message message) {
        // La implementación real está en el aspecto
        // Esta es solo una implementación por defecto en caso de que el aspecto no esté activo

        message.setFilteredContent(message.getContent());
        message.setHasWarning(false);

        return message;
    }
}