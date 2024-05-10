package com.matriculasapi.matriculas.client.cursos;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8080/api/v1/cursos", name = "cursos")
public interface CursoClient {
    @GetMapping("/{nome}")
    Curso buscarCursosPorNome(@PathVariable String nome);

    @GetMapping("/id/{id}")
    Curso buscarCursosPorId(@PathVariable Long id);
}
