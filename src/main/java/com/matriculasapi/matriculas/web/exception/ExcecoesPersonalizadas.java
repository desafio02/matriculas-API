package com.matriculasapi.matriculas.web.exception;

import com.matriculasapi.matriculas.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ExcecoesPersonalizadas extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AlunoInativoException.class)
    public final ResponseEntity<MessageError> handleAlunoInativoException(AlunoInativoException ex,  HttpServletRequest request) {
        log.error("Erro na API", ex);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new MessageError(request, HttpStatus.CONFLICT, ex.getMessage()));
    }

    @ExceptionHandler(CpfJaCadstradoException.class)
    public final ResponseEntity<MessageError> handleCpfJaCadstradoException(CpfJaCadstradoException ex,  HttpServletRequest request) {
        log.error("Erro na API", ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new MessageError(request, HttpStatus.CONFLICT, ex.getMessage()));
    }

    @ExceptionHandler(CpfNaoEncontradoException.class)
    public final ResponseEntity<MessageError> handleCpfNaoEncontradoException(CpfNaoEncontradoException ex,  HttpServletRequest request) {
        log.error("Erro na API", ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new MessageError(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(CursoInativoException.class)
    public final ResponseEntity<MessageError> handleCursoInativoException(CursoInativoException ex,  HttpServletRequest request) {
        log.error("Erro na API", ex);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new MessageError(request, HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<MessageError> handleEntityNotFoundException(EntityNotFoundException ex,  HttpServletRequest request) {
        log.error("Erro na API", ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new MessageError(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(LimiteAlunosException.class)
    public final ResponseEntity<MessageError> handleLimiteAlunosException(LimiteAlunosException ex,  HttpServletRequest request) {
        log.error("Erro na API", ex);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new MessageError(request, HttpStatus.BAD_REQUEST, ex.getMessage()));
    }
}
