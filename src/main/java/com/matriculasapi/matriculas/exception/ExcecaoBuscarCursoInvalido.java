package com.matriculasapi.matriculas.exception;

import feign.FeignException;

public class ExcecaoBuscarCursoInvalido extends FeignException {

    public ExcecaoBuscarCursoInvalido(int status, String message) {
        super(status, message);
    }
}
