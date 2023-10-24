package br.com.tabacetabacaria.api.exceptions;

import br.com.tabacetabacaria.service.exceptions.DataIntegratyViolationException;
import br.com.tabacetabacaria.service.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class) //manipulação de excecssão
    public ResponseEntity<StandardError> objectNotFound
            (ObjectNotFoundException ex, HttpServletRequest request) {

        var erro = new StandardError(LocalDateTime.now(), NOT_FOUND.value(),
                ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(NOT_FOUND).body(erro);
    }

    @ExceptionHandler(DataIntegratyViolationException.class)
    public ResponseEntity<StandardError> dataIntegratyViolation
            (DataIntegratyViolationException ex, HttpServletRequest request) {

        var erro = new StandardError(LocalDateTime.now(), BAD_REQUEST.value(),
                ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(BAD_REQUEST).body(erro);
    }
}
