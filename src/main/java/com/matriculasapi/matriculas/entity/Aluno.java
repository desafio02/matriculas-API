package com.matriculasapi.matriculas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity @NoArgsConstructor
@AllArgsConstructor
@Table(name= "alunos")
@Getter @Setter
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nome;
    @Size(min = 11, max = 11)
    @Column
    private String cpf;
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
    @Column
    private boolean ativo = true;
    @Column
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    public enum Sexo {
        M, F;
    }
}


