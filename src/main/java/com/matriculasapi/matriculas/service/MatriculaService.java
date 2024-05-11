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

        if (matriculaRepository.countByCursoId(matricula.getCursoId()) >= 10) {
            throw new ExcecaoLimiteAlunos("Limite de 10 matrículas atingido, não é possível realizar a matrícula para esse curso");
        }

        try {
            return matriculaRepository.save(matricula);
        } catch (DataIntegrityViolationException e) {
            throw new ExcecaoDadoDuplicado("Aluno já cadastrado no curso informado", e);
        }

    }

    @Transactional
    public void alterarStatusAtivo(Long id) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Matrícula não encontrada"));
        if(matricula.isStatus()){
            matricula.setStatus(false);
        }else{
            matricula.setStatus(true);
        }
        matriculaRepository.save(matricula);
    }

    @Transactional(readOnly = true)
    public List<Matricula> buscarMatriculasPorCursoId(Long id) {
        return matriculaRepository.findByCursoId(id)
                .orElseThrow(() -> new EntityNotFoundException("Nenhuma matrícula encontrada para o curso solicitado"));
    }
}
