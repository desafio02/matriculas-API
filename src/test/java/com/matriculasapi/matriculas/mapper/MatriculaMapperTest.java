package com.matriculasapi.matriculas.mapper;

import com.matriculasapi.matriculas.client.cursos.CursoClient;
import com.matriculasapi.matriculas.entity.Aluno;
import com.matriculasapi.matriculas.entity.Matricula;
import com.matriculasapi.matriculas.service.AlunoService;
import com.matriculasapi.matriculas.service.MatriculaService;
import com.matriculasapi.matriculas.web.dto.AlunoCreateDto;
import com.matriculasapi.matriculas.web.dto.AlunoResponseDto;
import com.matriculasapi.matriculas.web.dto.MatriculaCreateDto;
import com.matriculasapi.matriculas.web.dto.MatriculaResponseDto;
import com.matriculasapi.matriculas.web.dto.mapper.AlunoMapper;
import com.matriculasapi.matriculas.web.dto.mapper.MatriculaMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings
public class MatriculaMapperTest {

    private final AlunoService alunoService;
    private final MatriculaService matriculaService;
    private final CursoClient cursoClient;

    public MatriculaMapperTest(@Mock AlunoService alunoService, @Mock MatriculaService matriculaService, @Mock CursoClient cursoClient) {
        this.alunoService = alunoService;
        this.matriculaService = matriculaService;
        this.cursoClient = cursoClient;
    }

    @Test
    public void testParaMatricula() {
        MatriculaCreateDto createDto = new MatriculaCreateDto(
                "Html/Css",
                "56254862042"
        );

        Matricula matricula = new Matricula(
                1L,
                1L,
                2L,
                true
        );

        when(MatriculaMapper.paraMatricula(createDto, alunoService, cursoClient)).thenReturn(matricula);

        Matricula sut = MatriculaMapper.paraMatricula(createDto, alunoService, cursoClient);

        assertThat(sut).isEqualTo(matricula);
        assertThat(sut.getId()).isEqualTo(matricula.getId());
        assertThat(sut.getAlunoId()).isEqualTo(matricula.getAlunoId());
        assertThat(sut.getCursoId()).isEqualTo(matricula.getCursoId());
    }
}

