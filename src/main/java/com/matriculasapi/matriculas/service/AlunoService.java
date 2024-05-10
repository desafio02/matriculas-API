package com.matriculasapi.matriculas.service;

import com.matriculasapi.matriculas.entity.Aluno;
import com.matriculasapi.matriculas.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public Aluno salvar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public Aluno alterarStatusAtivo(String cpf) {
        Aluno aluno = alunoRepository.findByCpf(cpf).orElseThrow(
                () -> new RuntimeException("Número de CPF não encontrado")
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
                () -> new RuntimeException("Número de CPF não encontrado")
        );
    }

    public Aluno buscarPorId(Long alunoId) {
        return alunoRepository.findById(alunoId).orElseThrow(
                () -> new RuntimeException("Número de Id não encontrado")
        );
    }
}
