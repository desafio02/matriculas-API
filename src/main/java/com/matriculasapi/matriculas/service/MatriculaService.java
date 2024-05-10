package com.matriculasapi.matriculas.service;

import com.matriculasapi.matriculas.client.cursos.CursoClient;
import com.matriculasapi.matriculas.entity.Matricula;
import com.matriculasapi.matriculas.repository.MatriculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final CursoClient cursoClient;
    private final AlunoService alunoService;

    public Matricula salvar(Matricula matricula) {
        if (!cursoClient.buscarCursosPorId(matricula.getCursoId()).isAtivo()) {
            throw new RuntimeException("Curso inativado, não é possível realizar a matrícula para esse curso");
        }

        if(!alunoService.buscarPorId(matricula.getAlunoId()).isAtivo()) {
            throw new RuntimeException("Aluno inativo, não é possível realizar a matrícula");
        }

        if (matriculaRepository.countByCursoId(matricula.getCursoId()) == 10) {
            throw new RuntimeException("Limite de 10 matrículas atingido, não é possível realizar a matrícula para esse curso");
        }

        return matriculaRepository.save(matricula);
    }

    public void alterarStatusAtivo(Long id) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));
        if(matricula.isStatus()){
            matricula.setStatus(false);
        }else{
            matricula.setStatus(true);
        }
        matriculaRepository.save(matricula);
    }

    public List<Matricula> buscarMatriculasPorCursoId(Long id) {
        return matriculaRepository.findByCursoId(id)
                .orElseThrow(() -> new RuntimeException("Nenhuma matrícula encontrada para o curso solicitado"));
    }
}
