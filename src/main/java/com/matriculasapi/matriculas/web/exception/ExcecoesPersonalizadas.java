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

import java.net.ConnectException;

@Slf4j
@RestControllerAdvice
public class ExcecoesPersonalizadas extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ExcecaoAlunoInativo.class)
    public final ResponseEntity<MensagemErro> handleAlunoInativoException(ExcecaoAlunoInativo ex, HttpServletRequest request) {
        log.error("Erro na API", ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new MensagemErro(request, HttpStatus.CONFLICT, ex.getMessage()));
    }

    @ExceptionHandler(ExcecaoDadoDuplicado.class)
    public final ResponseEntity<MensagemErro> handleCpfJaCadstradoException(ExcecaoDadoDuplicado ex, HttpServletRequest request) {
        log.error("Erro na API", ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new MensagemErro(request, HttpStatus.CONFLICT, ex.getMessage()));
    }

    @ExceptionHandler(ExcecaoCpfNaoEncontrado.class)
    public final ResponseEntity<MensagemErro> handleCpfNaoEncontradoException(ExcecaoCpfNaoEncontrado ex, HttpServletRequest request) {
        log.error("Erro na API", ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new MensagemErro(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(ExcecaoCursoInativo.class)
    public final ResponseEntity<MensagemErro> handleCursoInativoException(ExcecaoCursoInativo ex, HttpServletRequest request) {
        log.error("Erro na API", ex);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new MensagemErro(request, HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    @ExceptionHandler(ExcecaoNaoEncontrado.class)
    public final ResponseEntity<MensagemErro> handleEntityNotFoundException(ExcecaoNaoEncontrado ex, HttpServletRequest request) {
        log.error("Erro na API", ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new MensagemErro(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(ExcecaoLimiteAlunos.class)
    public final ResponseEntity<MensagemErro> handleLimiteAlunosException(ExcecaoLimiteAlunos ex, HttpServletRequest request) {
        log.error("Erro na API", ex);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new MensagemErro(request, HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<Object> handleConnectException(HttpServletRequest request, ConnectException ex) {
        log.error("Erro de conexão com API Cursos", ex);
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .contentType(MediaType.APPLICATION_JSON)
                .body("API Cursos não disponível");
    }
}
