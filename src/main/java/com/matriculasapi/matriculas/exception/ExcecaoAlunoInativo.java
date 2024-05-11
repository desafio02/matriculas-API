package com.matriculasapi.matriculas.exception;

import lombok.Getter;

@Getter
public class ExcecaoAlunoInativo extends RuntimeException{

            public ExcecaoAlunoInativo(String message){
                super(message);
            }
}
