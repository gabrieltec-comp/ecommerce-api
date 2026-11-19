package com.gabriel.ecommerce_api.exception;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroPadrao> tratarRecursoNaoEncontrado(
            RecursoNaoEncontradoException ex, HttpServletRequest request)
    {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErroPadrao erro = new ErroPadrao(
                Instant.now(),
                status.value(),
                "Recurso não encontrado",
                ex.getMessage(),
                request.getRequestURI(),
                null);

        return ResponseEntity.status(status).body(erro);
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<ErroPadrao> tratarRegraNegocio(
            RegraNegocioException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErroPadrao erro = new ErroPadrao(
                Instant.now(),
                status.value(),
                "Erro de regra de negócio",
                ex.getMessage(),
                request.getRequestURI(),
                null
        );
        return ResponseEntity.status(status).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroPadrao> tratarValidacao(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Map<String, String> errosCampos = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errosCampos.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ErroPadrao erro = new ErroPadrao(
                Instant.now(),
                status.value(),
                "Erro de validação nos dados enviados",
                "Verifique os campos inválidos",
                request.getRequestURI(),
                errosCampos
        );
        return ResponseEntity.status(status).body(erro);
    }

}
