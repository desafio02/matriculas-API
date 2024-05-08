package com.matriculasapi.matriculas.service;

import com.matriculasapi.matriculas.entity.Matricula;
import com.matriculasapi.matriculas.repository.MatriculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;

    public Matricula salvar(Matricula matricula) {
        return matriculaRepository.save(matricula);
    }

    public Matricula procurarPorNomeDoCurso(String curso) {
        return matriculaRepository.findByCurso_Nome(curso).orElseThrow(() -> new RuntimeException("Curso não encontrado"));
    }
}
