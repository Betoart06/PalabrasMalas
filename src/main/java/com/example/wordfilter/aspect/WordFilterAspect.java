package com.example.wordfilter.aspect;

import com.example.wordfilter.model.Message;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.logging.Logger;

/**
 * Aspecto que implementa la lógica de filtrado de palabras prohibidas.
 */
@Aspect
@Component
public class WordFilterAspect {

    private static final Logger logger = Logger.getLogger(WordFilterAspect.class.getName());
    private final List<String> prohibitedWords;

    @Autowired
    public WordFilterAspect(List<String> prohibitedWords) {
        this.prohibitedWords = prohibitedWords;
    }

    /**
     * Método que se ejecuta alrededor del método filterMessage en WordFilterService.
     * Implementa la lógica de filtrado de palabras.
     */
    @Around("execution(* com.example.wordfilter.service.WordFilterService.filterMessage(..))")
    public Object filterProhibitedWords(ProceedingJoinPoint joinPoint) throws Throwable {
        // Obtener el mensaje a filtrar
        Object[] args = joinPoint.getArgs();
        Message message = (Message) args[0];
        String content = message.getContent();

        logger.info("Filtrando mensaje: " + content);

        // Contar palabras prohibidas
        int prohibitedCount = 0;
        for (String word : prohibitedWords) {
            Pattern pattern = Pattern.compile("\\b" + word + "\\b", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(content);

            while (matcher.find()) {
                prohibitedCount++;
                logger.info("Palabra prohibida encontrada: " + word);
            }
        }

        // Filtrar el mensaje según las reglas
        if (prohibitedCount > 3) {
            // Si hay más de 3 palabras prohibidas, mostrar mensaje de advertencia
            message.setFilteredContent("⚠️ El mensaje contiene demasiadas palabras prohibidas. Por favor, revise su lenguaje.");
            message.setHasWarning(true);
            logger.warning("Mensaje con demasiadas palabras prohibidas: " + prohibitedCount);
        } else {
            // Sustituir palabras prohibidas por caracteres
            String filteredContent = content;
            for (String word : prohibitedWords) {
                Pattern pattern = Pattern.compile("\\b" + word + "\\b", Pattern.CASE_INSENSITIVE);
                filteredContent = pattern.matcher(filteredContent).replaceAll("!#$@%");
            }

            message.setFilteredContent(filteredContent);
            message.setHasWarning(false);

            if (prohibitedCount > 0) {
                logger.info("Mensaje filtrado con " + prohibitedCount + " palabras prohibidas");
            }
        }

        // Continuar con la ejecución del método original
        return joinPoint.proceed(new Object[]{message});
    }
}