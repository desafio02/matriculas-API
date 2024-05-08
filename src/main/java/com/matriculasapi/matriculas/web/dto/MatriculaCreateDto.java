package com.matriculasapi.matriculas.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatriculaCreateDto {
    @NotBlank
    private String curso;

    @NotBlank
    @CPF
    @Size(min = 11, max = 11)
    private String cpf;
}
