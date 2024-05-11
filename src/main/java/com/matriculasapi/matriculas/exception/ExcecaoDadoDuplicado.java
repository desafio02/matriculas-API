package com.matriculasapi.matriculas.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class ExcecaoDadoDuplicado extends RuntimeException {
    public ExcecaoDadoDuplicado(String message, DataIntegrityViolationException e){
        super(message);
    }
}
