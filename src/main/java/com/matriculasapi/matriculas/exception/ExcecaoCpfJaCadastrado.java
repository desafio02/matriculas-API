package com.matriculasapi.matriculas.exception;

import lombok.Getter;
import org.springframework.dao.DataAccessException;

@Getter
public class ExcecaoCpfJaCadastrado extends RuntimeException{

    public ExcecaoCpfJaCadastrado(String message, DataAccessException e){
        super(message);
    }
}