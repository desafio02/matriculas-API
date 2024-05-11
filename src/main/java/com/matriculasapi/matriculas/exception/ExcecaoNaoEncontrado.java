package com.matriculasapi.matriculas.exception;

import lombok.Getter;

@Getter
public class ExcecaoNaoEncontrado extends RuntimeException{

        public ExcecaoNaoEncontrado(String message){
            super(message);
        }
}
