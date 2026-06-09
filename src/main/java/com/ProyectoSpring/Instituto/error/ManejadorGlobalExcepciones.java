package com.ProyectoSpring.Instituto.error;


import com.ProyectoSpring.Instituto.dto.response.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ManejadorGlobalExcepciones extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoEncontradoExcepcion.class)
    public ResponseEntity<ErrorMessage> notFoundException(NoEncontradoExcepcion exception) {
        // Creamos un objeto ErrorMessage con el código de estado y el mensaje
        // que escribimos en el throw new NotFoundException(...)
        ErrorMessage message = new ErrorMessage(exception.getStatus(), exception.getMessage());

        // Devolvemos una respuesta HTTP con el código de estado correspondiente
        // (por ejemplo, 404 NOT_FOUND) y el mensaje en el cuerpo
        return ResponseEntity.status(exception.getStatus()).body(message);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,     // Excepción con todos los errores de validación
            HttpHeaders headers,                    // Cabeceras HTTP (no las usamos, pero Spring las pasa)
            HttpStatusCode status,                  // Código HTTP (ej: 400 BAD_REQUEST)
            WebRequest request) {                   // Información de la solicitud (no la usamos)

        // Este Map va a guardar: nombreDelCampo → mensajeDeError
        Map<String, Object> errors = new HashMap<>();

        // ex.getBindingResult() → contiene TODOS los resultados de la validación
        // .getFieldErrors() → obtiene SOLO los errores de los campos individuales (no errores generales como "la contraseña no coincide")
        // .forEach(error -> { ... }) → recorre UNO POR UNO los errores encontrados
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            // error.getField()      → el nombre del campo que falló (ej: "numero")
            // error.getDefaultMessage() → el mensaje que escribimos en la anotación (ej: "Por favor, ingrese el número del legajo.")
            // Agregamos cada par (campo → mensaje) al Map
            errors.put(error.getField(), error.getDefaultMessage());
        });

        // Devolvemos HTTP 400 BAD_REQUEST con el Map de errores como cuerpo
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
