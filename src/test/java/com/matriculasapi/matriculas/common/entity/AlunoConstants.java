package com.matriculasapi.matriculas.common.entity;

import com.matriculasapi.matriculas.entity.Aluno;

import java.time.LocalDate;

public class AlunoConstants {
    public static final Aluno ALUNO = new Aluno(
            1L,
            "Joao Silva",
            "09759576007",
            LocalDate.of(2024, 5, 11),
            true,
            Aluno.Sexo.M
    );
    public static final Aluno ALUNO_INVALIDO = new Aluno(
            1L,
            "",
            "",
            LocalDate.of(2024, 5, 11),
            true,
            Aluno.Sexo.M
    );
}
