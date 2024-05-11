package com.matriculasapi.matriculas.exception;

import lombok.Getter;

@Getter
public class ExcecaoCursoInativo extends RuntimeException {

    public ExcecaoCursoInativo(String message) {
        super(message);
    }
}
