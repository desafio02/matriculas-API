package com.matriculasapi.matriculas.client.cursos;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Curso {
    @Id
    private Long id;
    private String nome;
    private String professor;
    private boolean ativo;
}
