package com.matriculasapi.matriculas.service;

import com.matriculasapi.matriculas.entity.Aluno;
import com.matriculasapi.matriculas.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public Aluno salvar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public Aluno alterarStatusAtivo(String cpf) {

        Optional<Aluno> alunoOptional = alunoRepository.findByCpf(cpf);

        try{
            if (alunoOptional.isPresent()) {

                Aluno aluno = alunoOptional.get();
                if(aluno.isAtivo()) {
                    aluno.setAtivo(false);
                } else {
                    aluno.setAtivo(true);
                }
                return alunoRepository.save(aluno);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
