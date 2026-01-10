package com.example.prueba_tecnica.infrastructure.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Captura errores de validación de DTOs (@Valid)
    // Esto es vital para las validaciones de Identificación de 10 dígitos [cite: 28]
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        // CORRECCIÓN: Se cambia BAD_VALUE por BAD_REQUEST
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // 2. Captura errores de lógica de negocio (RuntimeExceptions)
    // Útil para retornar el mensaje "Usuario bloqueado" tras 3 intentos [cite: 41-42]
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeExceptions(RuntimeException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // 3. Captura errores generales del sistema
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralExceptions(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Ocurrió un error inesperado en el servidor");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
