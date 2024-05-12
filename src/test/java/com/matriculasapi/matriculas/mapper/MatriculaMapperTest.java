package com.matriculasapi.matriculas.mapper;

import com.matriculasapi.matriculas.client.cursos.CursoClient;
import com.matriculasapi.matriculas.entity.Aluno;
import com.matriculasapi.matriculas.entity.Matricula;
import com.matriculasapi.matriculas.service.AlunoService;
import com.matriculasapi.matriculas.web.dto.AlunoResponseDto;
import com.matriculasapi.matriculas.web.dto.MatriculaCreateDto;
import com.matriculasapi.matriculas.web.dto.MatriculaResponseDto;
import com.matriculasapi.matriculas.web.dto.mapper.MatriculaMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RequiredArgsConstructor
public class MatriculaMapperTest {

    private final CursoClient cursoClient;

    private final AlunoService alunoService;


}
