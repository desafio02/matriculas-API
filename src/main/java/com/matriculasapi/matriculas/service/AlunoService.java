package com.matriculasapi.matriculas.service;

import com.matriculasapi.matriculas.entity.Aluno;
import com.matriculasapi.matriculas.entity.Matricula;
import com.matriculasapi.matriculas.exception.ExcecaoCpfNaoEncontrado;
import com.matriculasapi.matriculas.exception.ExcecaoDadoDuplicado;
import com.matriculasapi.matriculas.repository.AlunoRepository;
import com.matriculasapi.matriculas.repository.MatriculaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final MatriculaRepository matriculaRepository;


    @Transactional
    public Aluno salvar(Aluno aluno) {
        try {
            return alunoRepository.save(aluno);
        } catch (DataIntegrityViolationException e) {
            throw new ExcecaoDadoDuplicado(String.format("Aluno %s já cadastrado", aluno.getNome()));
        }
    }

    @Transactional
    public void alterarStatusAtivo(String cpf) {
        Aluno aluno = buscarPorCpf(cpf);
        aluno.setAtivo(!aluno.isAtivo());

        List<Matricula> matriculasAluno = matriculaRepository.findByAlunoId(aluno.getId())
                .orElseThrow(() -> new EntityNotFoundException("Nenhuma matrícula encontrada para o aluno solicitado"));

        List<Matricula> matriculasCurso = new ArrayList<>();

        if (aluno.isAtivo()) {
            matriculasAluno.forEach(matricula -> {
                long countAtivas = matriculaRepository.findByCursoId(matricula.getCursoId())
                        .orElseThrow(() -> new EntityNotFoundException("Nenhuma matrícula encontrada para o curso solicitado"))
                        .stream()
                        .filter(x -> x.isStatus())
                        .count();

                if (countAtivas < 10) {
                    matricula.setStatus(true);
                    matriculasCurso.add(matricula);
                }
            });
        } else {
            matriculasAluno.forEach(matricula -> {
                matricula.setStatus(false);
                matriculasCurso.add(matricula);
            });
        }

        matriculaRepository.saveAll(matriculasCurso);
        alunoRepository.save(aluno);
    }

    @Transactional(readOnly = true)
    public Aluno buscarPorCpf(String cpf) {
        return alunoRepository.findByCpf(cpf).orElseThrow(
                () -> new ExcecaoCpfNaoEncontrado("Número de CPF não encontrado")
        );
    }

    @Transactional(readOnly = true)
    public Aluno buscarPorId(Long alunoId) {
        return alunoRepository.findById(alunoId).orElseThrow(
                () -> new EntityNotFoundException("Número de Id não encontrado")
        );
    }
}
