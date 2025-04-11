package com.example.wordfilter.controller;

import com.example.wordfilter.model.Message;
import com.example.wordfilter.service.WordFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controlador para manejar las peticiones HTTP.
 */
@Controller
public class MessageController {

    private final WordFilterService wordFilterService;

    @Autowired
    public MessageController(WordFilterService wordFilterService) {
        this.wordFilterService = wordFilterService;
    }

    /**
     * Maneja las peticiones GET a la página principal.
     */
    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("message", new Message());
        return "index";
    }

    /**
     * Maneja las peticiones POST para procesar un mensaje.
     */
    @PostMapping("/filter")
    public String processMessage(@ModelAttribute Message message, Model model) {
        Message filteredMessage = wordFilterService.filterMessage(message);
        model.addAttribute("message", filteredMessage);
        return "index";
    }
}