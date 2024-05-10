package com.matriculasapi.matriculas.web.dto.mapper;

import com.matriculasapi.matriculas.client.cursos.Curso;
import com.matriculasapi.matriculas.client.cursos.CursoClient;
import com.matriculasapi.matriculas.entity.Matricula;
import com.matriculasapi.matriculas.service.AlunoService;
import com.matriculasapi.matriculas.web.dto.MatriculaCreateDto;
import com.matriculasapi.matriculas.web.dto.MatriculaResponseDto;

public class MatriculaMapper {

    public static Matricula paraMatricula(MatriculaCreateDto dto, AlunoService alunoService, CursoClient cursoClient) {
        Matricula matricula = new Matricula();

        matricula.setCursoId(cursoClient.buscarCursosPorNome(dto.getCurso()).getId());
        matricula.setAlunoId(alunoService.buscarPorCpf(dto.getCpf()).getId());

        return matricula;
    }
    public static MatriculaResponseDto paraDto(Matricula matricula, AlunoService alunoService, CursoClient cursoClient) {
        MatriculaResponseDto dto = new MatriculaResponseDto();
        Curso curso = cursoClient.buscarCursosPorId(matricula.getCursoId());

        dto.setCurso(curso.getNome());
        dto.setProfessor(curso.getProfessor());
        dto.setAluno(AlunoMapper.paraDto(alunoService.buscarPorId(matricula.getAlunoId())));

        return dto;
    }
}
