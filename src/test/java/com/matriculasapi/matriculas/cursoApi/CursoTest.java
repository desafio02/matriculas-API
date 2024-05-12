package com.matriculasapi.matriculas.cursoApi;

import com.matriculasapi.matriculas.client.cursos.Curso;
import com.matriculasapi.matriculas.client.cursos.CursoClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CursoTest {
    @Mock
    private CursoClient cursoClient;
    @Test
    public void createPlanet_WithValidData_ReturnsPlanet() {

        Curso curso = new Curso (
                1L,
                "Html/Css",
                "marcio da silva",
                true
        );

        when(cursoClient.buscarCursosPorId(1L)).thenReturn(curso);

        Curso sut = cursoClient.buscarCursosPorId(1L);

        assertThat(sut).isEqualTo(curso);
        assertThat(sut.getId()).isEqualTo(curso.getId());
        assertThat(sut.getNome()).isEqualTo(curso.getNome());
        assertThat(sut.getProfessor()).isEqualTo(curso.getProfessor());
    }
}

