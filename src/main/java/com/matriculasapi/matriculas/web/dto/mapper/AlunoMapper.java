package com.matriculasapi.matriculas.web.dto.mapper;

import com.matriculasapi.matriculas.entity.Aluno;
import com.matriculasapi.matriculas.web.dto.AlunoCreateDto;
import com.matriculasapi.matriculas.web.dto.AlunoResponseDto;
import org.modelmapper.ModelMapper;

public class AlunoMapper {

    public static Aluno paraAluno(AlunoCreateDto createDto) {
        return new ModelMapper().map(createDto, Aluno.class);
    }

    public static AlunoResponseDto paraDto(Aluno aluno) {
        return new ModelMapper().map(aluno, AlunoResponseDto.class);
    }
}


