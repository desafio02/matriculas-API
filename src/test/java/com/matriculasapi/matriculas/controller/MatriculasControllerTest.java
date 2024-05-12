package com.matriculasapi.matriculas.controller;

import com.matriculasapi.matriculas.client.cursos.Curso;
import com.matriculasapi.matriculas.client.cursos.CursoClient;
import com.matriculasapi.matriculas.entity.Aluno;
import com.matriculasapi.matriculas.entity.Matricula;
import com.matriculasapi.matriculas.repository.MatriculaRepository;
import com.matriculasapi.matriculas.service.AlunoService;
import com.matriculasapi.matriculas.service.MatriculaService;
import com.matriculasapi.matriculas.web.controller.MatriculaController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.matriculasapi.matriculas.common.entity.AlunoConstants.ALUNO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MatriculasControllerTest {

    @Mock
    private MatriculaRepository matriculaRepository;

    @Mock
    private CursoClient cursoClient;

    @Mock
    private AlunoService alunoService;

    @Mock
    private MatriculaService matriculaService;

    @InjectMocks
    private MatriculaController matriculaController;

    @Test
    public void testSalvarMatricula() {
        MatriculaService matriculaService = new MatriculaService(matriculaRepository, cursoClient, alunoService);

        Matricula matricula = new Matricula();
        matricula.setCursoId(1L);
        matricula.setAlunoId(1L);

        Curso curso = new Curso();
        curso.setId(1L);
        curso.setNome("Testando");
        curso.setProfessor("Excelentíssimo Doutor Professor Testando");
        curso.setAtivo(true);

        when(cursoClient.buscarCursosPorId(1L)).thenReturn(curso);
        when(alunoService.buscarPorId(1L)).thenReturn(new Aluno());
        when(matriculaRepository.findByCursoId(1L)).thenReturn(Optional.of(Collections.emptyList()));
        when(matriculaRepository.save(matricula)).thenReturn(matricula);

        Matricula matriculaSalva = matriculaService.salvar(matricula);

        verify(matriculaRepository, times(1)).save(matricula);

        assertEquals(matricula, matriculaSalva);
    }


    @Test
    public void testAlterarStatusMatricula() {

        Long matriculaId = 1L;

        Matricula matricula = new Matricula();
        matricula.setId(matriculaId);
        matricula.setStatus(true);

        Matricula matriculaAlterada = new Matricula();
        matriculaAlterada.setId(matriculaId);
        matriculaAlterada.setStatus(false);

        doNothing().when(matriculaService).alterarStatusAtivo(matriculaId);

        ResponseEntity<Void> response = matriculaController.alterarStatusAtivo(matriculaId);

        verify(matriculaService, times(1)).alterarStatusAtivo(matriculaId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testBuscarMatriculasPorCursoId() { // esse aqui não está funcionando
        Long cursoId = 1L;
        Matricula matricula = new Matricula();
        matricula.setCursoId(cursoId);
        List<Matricula> matriculas = new ArrayList<>();
        matriculas.add(matricula);

        when(matriculaRepository.findByCursoId(cursoId)).thenReturn(Optional.of(matriculas));

        List<Matricula> matriculasEncontradas = matriculaService.buscarMatriculasPorCursoId(cursoId);

        verify(matriculaRepository, times(1)).findByCursoId(cursoId);

        assertEquals(matriculas, matriculasEncontradas);
    }

    @Test
    public void testBuscarMatriculasPorCursoIdNenhumaMatriculaEncontrada() {
        Long cursoId = 123L;
        List<Matricula> matriculas = matriculaService.buscarMatriculasPorCursoId(cursoId);
        assertTrue(matriculas.isEmpty());
    }
}
