package com.matriculasapi.matriculas.exception;

public class CpfNaoEncontradoException extends RuntimeException{

        public CpfNaoEncontradoException(String message){
            super(message);
        }
}
