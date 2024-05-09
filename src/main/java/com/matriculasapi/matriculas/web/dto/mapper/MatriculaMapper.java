package com.matriculasapi.matriculas.web.dto.mapper;

import com.matriculasapi.matriculas.entity.Matricula;
import com.matriculasapi.matriculas.web.dto.MatriculaCreateDto;
import com.matriculasapi.matriculas.web.dto.MatriculaResponseDto;
import org.modelmapper.ModelMapper;

public class MatriculaMapper {
    public static Matricula paraMatricula(MatriculaCreateDto createDto) {
        return new ModelMapper().map(createDto, Matricula.class);
    }

    public static MatriculaResponseDto paraDto(Matricula matricula) {
        return new ModelMapper().map(matricula, MatriculaResponseDto.class);
    }
}
