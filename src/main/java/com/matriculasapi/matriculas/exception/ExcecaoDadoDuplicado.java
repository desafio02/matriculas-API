package com.matriculasapi.matriculas.exception;

import lombok.Getter;

@Getter
public class ExcecaoDadoDuplicado extends RuntimeException {
    public ExcecaoDadoDuplicado(String message){
        super(message);
    }
}
