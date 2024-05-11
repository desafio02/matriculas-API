package com.matriculasapi.matriculas.web.exception;

import com.matriculasapi.matriculas.exception.ExcecaoErroDesconhecido;
import com.matriculasapi.matriculas.exception.ExcecaoNaoEncontrado;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ExcecoesDecodificador implements ErrorDecoder {
    @Override
    public Exception decode(String method, Response response) {
        log.info("Decodificador de exceção: {}, {}", method, response);


        if(method.contains("buscarCursosPorNome")) {
            return new ExcecaoNaoEncontrado("Curso não encontrado");
        }

        return new ExcecaoErroDesconhecido();
    }
}
