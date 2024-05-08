package com.matriculasapi.matriculas.repository;

import com.matriculasapi.matriculas.entity.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    Optional<Matricula> findByCurso_Nome(String curso);
}
