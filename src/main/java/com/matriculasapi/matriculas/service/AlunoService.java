package com.matriculasapi.matriculas.service;

import com.matriculasapi.matriculas.entity.Aluno;
import com.matriculasapi.matriculas.exception.CpfJaCadstradoException;
import com.matriculasapi.matriculas.exception.CpfNaoEncontradoException;
import com.matriculasapi.matriculas.repository.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public Aluno salvar(Aluno aluno) {
        try {
            return alunoRepository.save(aluno);
        } catch (DataAccessException e) {
            throw new CpfJaCadstradoException("Aluno %s já cadastrado" + aluno.getNome()) {
            };
        }
    }

    public Aluno alterarStatusAtivo(String cpf) {
        Aluno aluno = alunoRepository.findByCpf(cpf).orElseThrow(
                () -> new CpfNaoEncontradoException("Número de CPF não encontrado")
        );

        if (aluno.isAtivo()) {
            aluno.setAtivo(false);
        } else {
            aluno.setAtivo(true);
        }

        return alunoRepository.save(aluno);
    }

    public Aluno buscarPorCpf(String cpf) {
        return alunoRepository.findByCpf(cpf).orElseThrow(
                () -> new CpfNaoEncontradoException("Número de CPF não encontrado")
        );
    }

    public Aluno buscarPorId(Long alunoId) {
        return alunoRepository.findById(alunoId).orElseThrow(
                () -> new EntityNotFoundException("Número de Id não encontrado")
        );
    }
}
