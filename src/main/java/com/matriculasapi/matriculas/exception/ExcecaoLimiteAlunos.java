package com.matriculasapi.matriculas.exception;

import lombok.Getter;

@Getter
public class ExcecaoLimiteAlunos extends RuntimeException{

            public ExcecaoLimiteAlunos(String message){
                super(message);
            }
}
