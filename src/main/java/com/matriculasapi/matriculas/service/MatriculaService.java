package com.matriculasapi.matriculas.service;

import com.matriculasapi.matriculas.client.cursos.Curso;
import com.matriculasapi.matriculas.client.cursos.CursoClient;
import com.matriculasapi.matriculas.entity.Aluno;
import com.matriculasapi.matriculas.entity.Matricula;
import com.matriculasapi.matriculas.repository.MatriculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final CursoClient cursoClient;
    private final AlunoService alunoService;


    public Matricula salvar(String nomeCurso, String cpf) {
        Matricula matricula = new Matricula();
        Curso curso = cursoClient.buscarCursos(nomeCurso);
        Aluno aluno = alunoService.buscarPorCpf(cpf);
        if(curso == null) {
            throw new RuntimeException("Curso não encontrado");
        }
        if (aluno == null){
            throw new RuntimeException("Aluno não encontrado");
        }else{
            matricula.setCursoId(curso.getId());
            matricula.setAlunoId(aluno.getId());
            return matriculaRepository.save(matricula);
        }

    }

    public void alterarStatusAtivo(Long id) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));
        if(matricula.getStatus() == true){
            matricula.setStatus(false);
        }else{
            matricula.setStatus(true);
        }
        matriculaRepository.save(matricula);
    }
}
