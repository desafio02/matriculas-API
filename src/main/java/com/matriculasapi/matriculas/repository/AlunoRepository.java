package com.matriculasapi.matriculas.repository;

import com.matriculasapi.matriculas.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
