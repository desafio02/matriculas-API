package com.matriculasapi.matriculas.repository;

import com.matriculasapi.matriculas.entity.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    int countByCursoId(Long cursoId);

    Optional<List<Matricula>> findByCursoId(Long id);
}
