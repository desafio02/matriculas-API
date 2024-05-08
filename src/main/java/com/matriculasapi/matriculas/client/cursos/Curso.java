package com.matriculasapi.matriculas.client.cursos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Curso {
    private Long id;
    private String nome;
    private String professor;
    private boolean ativo;
}
