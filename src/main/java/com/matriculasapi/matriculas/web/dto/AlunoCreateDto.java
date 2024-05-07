package com.matriculasapi.matriculas.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlunoCreateDto {

    @NotBlank
    private String nome;

    @NotBlank
    @CPF
    @Size(min = 11, max = 11)
    private String cpf;

    @NotBlank
    private LocalDate dataNascimento;

    @NotBlank
    @Pattern(regexp = "M|F")
    private String sexo;

}


