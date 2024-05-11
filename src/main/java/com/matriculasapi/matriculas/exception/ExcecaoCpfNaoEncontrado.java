package com.matriculasapi.matriculas.exception;

import lombok.Getter;

@Getter
public class ExcecaoCpfNaoEncontrado extends RuntimeException{

        public ExcecaoCpfNaoEncontrado(String message){
            super(message);
        }
}
