package com.matriculasapi.matriculas.service;

import com.matriculasapi.matriculas.client.cursos.CursoClient;
import com.matriculasapi.matriculas.entity.Matricula;
import com.matriculasapi.matriculas.exception.*;
import com.matriculasapi.matriculas.repository.MatriculaRepository;
import feign.FeignException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final CursoClient cursoClient;
    private final AlunoService alunoService;

    @Transactional
    public Matricula salvar(Matricula matricula) {
        try {
            if (!cursoClient.buscarCursosPorId(matricula.getCursoId()).isAtivo()) {
                throw new ExcecaoCursoInativo("Curso inativado, não é possível realizar a matrícula para esse curso");
            }
        } catch (FeignException.NotFound e) {
            throw new ExcecaoNaoEncontrado(e.getMessage());
        }

        if(!alunoService.buscarPorId(matricula.getAlunoId()).isAtivo()) {
            throw new ExcecaoAlunoInativo("Aluno inativo, não é possível realizar a matrícula");
        }

        List<Matricula> matriculas = buscarMatriculasPorCursoId(matricula.getCursoId());

        long alunosAtivos = matriculas.stream().filter(x -> alunoService.buscarPorId(x.getAlunoId()).isAtivo()).count();

        if (alunosAtivos >= 10) {
            throw new ExcecaoLimiteAlunos("Limite de 10 matrículas atingido, não é possível realizar a matrícula para esse curso");
        }

        matriculas.forEach(x -> {
                    if (x.getAlunoId().equals(matricula.getAlunoId())) {
                        throw new ExcecaoDadoDuplicado("Aluno já cadastrado no curso informado");
                    }
        });

        return matriculaRepository.save(matricula);

    }

    @Transactional
    public void alterarStatusAtivo(Long id) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Matrícula não encontrada"));
        matricula.setStatus(!matricula.isStatus());
        matriculaRepository.save(matricula);
    }

    @Transactional(readOnly = true)
    public List<Matricula> buscarMatriculasPorCursoId(Long id) {
        return matriculaRepository.findByCursoId(id)
                .orElseThrow(() -> new EntityNotFoundException("Nenhuma matrícula encontrada para o curso solicitado"));
    }

    @Transactional(readOnly = true)
    public List<Matricula> buscarMatriculasPorAlunoId(Long id) {
        return matriculaRepository.findByAlunoId(id)
                .orElseThrow(() -> new EntityNotFoundException("Nenhuma matrícula encontrada para o aluno solicitado"));
    }
}
