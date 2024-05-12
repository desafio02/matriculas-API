package com.matriculasapi.matriculas.common.dto;

import com.matriculasapi.matriculas.web.dto.AlunoCreateDto;
import com.matriculasapi.matriculas.web.dto.AlunoResponseDto;

import java.time.LocalDate;

public class AlunoDtoConstants {
    public static final AlunoCreateDto ALUNOCREATEDTO = new AlunoCreateDto(
            "Jalim Rabei",
            "09759576007",
            LocalDate.of(2024, 5, 11),
            "M"
    );
    public static final AlunoResponseDto ALUNORESPONSEDTO = new AlunoResponseDto(
            "Jalim Rabei",
            "M",
            true
    );
}
