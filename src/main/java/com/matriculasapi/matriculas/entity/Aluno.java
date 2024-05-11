package com.matriculasapi.matriculas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

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
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;
    @Column(name = "data_nascimento")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
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


