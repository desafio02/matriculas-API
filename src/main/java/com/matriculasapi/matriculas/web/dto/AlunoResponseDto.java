package com.matriculasapi.matriculas.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlunoResponseDto {
    private String nome;
    private String sexo;
    private boolean ativo;
}


