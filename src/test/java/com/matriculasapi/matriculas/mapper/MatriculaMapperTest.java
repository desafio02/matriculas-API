package com.matriculasapi.matriculas.mapper;

import com.matriculasapi.matriculas.client.cursos.Curso;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        MatriculaCreateDto createDto = new MatriculaCreateDto("Html/Css", "56254862042");
        Long alunoId = 1L;
        Long cursoId = 1L;

        Curso curso = new Curso(cursoId, "Html/Css", "Professor Teste", true);
        when(cursoClient.buscarCursosPorNome("Html/Css")).thenReturn(curso);

        Aluno aluno = new Aluno();
        aluno.setId(alunoId);
        when(alunoService.buscarPorCpf("56254862042")).thenReturn(aluno);

        Matricula matricula = MatriculaMapper.paraMatricula(createDto, alunoService, cursoClient);

        assertEquals(cursoId, matricula.getCursoId());
        assertEquals(alunoId, matricula.getAlunoId());
        assertTrue(matricula.isStatus());
    }
}

