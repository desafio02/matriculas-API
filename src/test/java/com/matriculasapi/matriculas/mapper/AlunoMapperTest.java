package com.matriculasapi.matriculas.mapper;

import com.matriculasapi.matriculas.entity.Aluno;
import com.matriculasapi.matriculas.web.dto.AlunoCreateDto;
import com.matriculasapi.matriculas.web.dto.AlunoResponseDto;
import com.matriculasapi.matriculas.web.dto.mapper.AlunoMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlunoMapperTest {

    @Test
    public void testParaAluno() {
        AlunoCreateDto createDto = new AlunoCreateDto(
                "João",
                "17115646058",
                LocalDate.of(20000, 5, 10),
                "M"
        );

        Aluno aluno = AlunoMapper.paraAluno(createDto);

        assertEquals("João", aluno.getNome());
        assertEquals("17115646058", aluno.getCpf());
        assertEquals(LocalDate.of(20000, 5, 10), aluno.getDataNascimento());
        assertEquals(Aluno.Sexo.M, aluno.getSexo());
    }

    @Test
    public void testParaDto() {
        Aluno aluno = new Aluno();
        aluno.setNome("Maria");
        aluno.setSexo(Aluno.Sexo.F);
        aluno.setAtivo(true);

        AlunoResponseDto responseDto = AlunoMapper.paraDto(aluno);

        assertEquals("Maria", responseDto.getNome());
        assertEquals("F", responseDto.getSexo());
        assertEquals(true, responseDto.isAtivo());
    }
}
