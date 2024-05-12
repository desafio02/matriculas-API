package com.matriculasapi.matriculas.service;

import com.matriculasapi.matriculas.entity.Aluno;
import com.matriculasapi.matriculas.exception.ExcecaoCpfNaoEncontrado;
import com.matriculasapi.matriculas.repository.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.matriculasapi.matriculas.common.entity.AlunoConstants.ALUNO_INVALIDO;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static com.matriculasapi.matriculas.common.entity.AlunoConstants.ALUNO;

@ExtendWith(MockitoExtension.class)
public class AlunoServiceTest {
    @InjectMocks
    private AlunoService alunoService;

    @Mock
    private AlunoRepository alunoRepository;

    @Test
    public void criarAluno_ComDadosValidos_ReturnarAluno() {
        when(alunoRepository.save(ALUNO)).thenReturn(ALUNO);

        Aluno sut = alunoService.salvar(ALUNO);

        assertThat(sut).isEqualTo(ALUNO);
    }

    @Test
    public void criarAluno_ComDadosInvalidos_RetornarExcecao() {
        when(alunoRepository.save(ALUNO_INVALIDO)).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> alunoService.salvar(ALUNO_INVALIDO));
    }

    @Test
    public void retornarAluno_ComIdExistente_RetornarAluno() {
        when(alunoRepository.findById(1L)).thenReturn(Optional.of(ALUNO));

        Optional<Aluno> sut = Optional.ofNullable(alunoService.buscarPorId(1L));

        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(ALUNO);
    }

    @Test
    public void retornarAluno_ComCpfExistente_RetornarAluno() {
        when(alunoRepository.findByCpf("09759576007")).thenReturn(Optional.of(ALUNO));

        Optional<Aluno> sut = Optional.ofNullable(alunoService.buscarPorCpf("09759576007"));

        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(ALUNO);
    }

    @Test
    public void retornarAluno_ComIdInexistente_ReturnoVazio() {
        when(alunoRepository.findById(1L)).thenReturn(Optional.empty());

        Throwable thrown = assertThrows(EntityNotFoundException.class, () -> alunoService.buscarPorId(1L));

        assertEquals("Número de Id não encontrado", thrown.getMessage());
    }

    @Test
    public void retornarAluno_ComCpfInexistente_ReturnoVazio() {
        when(alunoRepository.findByCpf("82746541020")).thenReturn(Optional.empty());

        Throwable thrown = assertThrows(ExcecaoCpfNaoEncontrado.class, () -> alunoService.buscarPorCpf("82746541020"));

        assertEquals("Número de CPF não encontrado", thrown.getMessage());
    }

    @Test
    public void AlterarStatus_ComCpfValido_ReturnoVazio() {
        when(alunoRepository.findByCpf("82746541020")).thenReturn(Optional.empty());

        Throwable thrown = assertThrows(ExcecaoCpfNaoEncontrado.class, () -> alunoService.buscarPorCpf("82746541020"));

        assertEquals("Número de CPF não encontrado", thrown.getMessage());
    }
}
