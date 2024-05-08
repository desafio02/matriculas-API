package com.matriculasapi.matriculas.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatriculaResponseDto {
    private String curso;
    private String professor;
    private Integer totalAlunos;
    private List<AlunoResponseDto> alunos;
}
