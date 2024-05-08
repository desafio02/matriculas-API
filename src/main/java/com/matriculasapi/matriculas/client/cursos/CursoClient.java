package com.matriculasapi.matriculas.client.cursos;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "http://localhost:8080/api/v1/cursos", name = "cursos")
public interface CursoClient {
    @GetMapping
    Curso buscarCursos();
}
