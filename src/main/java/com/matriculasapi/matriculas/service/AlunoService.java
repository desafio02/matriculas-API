package com.matriculasapi.matriculas.service;

import com.matriculasapi.matriculas.entity.Aluno;
import com.matriculasapi.matriculas.exception.ExcecaoCpfJaCadastrado;
import com.matriculasapi.matriculas.exception.ExcecaoCpfNaoEncontrado;
import com.matriculasapi.matriculas.repository.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;

    @Transactional
    public Aluno salvar(Aluno aluno) {
        try {
            return alunoRepository.save(aluno);
        } catch (DataAccessException e) {
            throw new ExcecaoCpfJaCadastrado("Aluno %s já cadastrado" + aluno.getNome(), e);
        }
    }

    @Transactional
    public Aluno alterarStatusAtivo(String cpf) {
        Aluno aluno = buscarPorCpf(cpf);

        if (aluno.isAtivo()) {
            aluno.setAtivo(false);
        } else {
            aluno.setAtivo(true);
        }

        return alunoRepository.save(aluno);
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
