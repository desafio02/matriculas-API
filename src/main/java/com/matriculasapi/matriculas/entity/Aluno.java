package entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity @NoArgsConstructor
@AllArgsConstructor
@Table(name= "alunos")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nome;
    @Size(min = 11, max = 11)
    @Column
    private String cpf;
    @Column(name = "data_nascimento")
    private Date dataNascimento;
    @Column
    private boolean ativo;
}

enum Sexo {
    MASCULINO, FEMININO;
}
