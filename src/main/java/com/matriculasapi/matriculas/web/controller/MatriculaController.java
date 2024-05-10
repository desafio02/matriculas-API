package com.matriculasapi.matriculas.web.controller;

import com.matriculasapi.matriculas.client.cursos.CursoClient;
import com.matriculasapi.matriculas.entity.Matricula;
import com.matriculasapi.matriculas.service.AlunoService;
import com.matriculasapi.matriculas.service.MatriculaService;
import com.matriculasapi.matriculas.web.dto.MatriculaCreateDto;
import com.matriculasapi.matriculas.web.dto.MatriculaResponseDto;
import com.matriculasapi.matriculas.web.dto.MatriculaResponseListAlunoDto;
import com.matriculasapi.matriculas.web.dto.mapper.AlunoMapper;
import com.matriculasapi.matriculas.web.dto.mapper.MatriculaMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/matriculas")
@RestController
public class MatriculaController {

    private final MatriculaService matriculaService;
    private final CursoClient cursoClient;
    private final AlunoService alunoService;

    @PostMapping
    public ResponseEntity<MatriculaResponseDto> salvarMatricula(@RequestBody @Valid MatriculaCreateDto dto){
        Matricula matricula = matriculaService.salvar(MatriculaMapper.paraMatricula(dto, alunoService, cursoClient));
        return ResponseEntity.status(HttpStatus.CREATED).body(MatriculaMapper.paraDto(matricula, alunoService, cursoClient));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> alterarStatusAtivo(@PathVariable Long id){
        matriculaService.alterarStatusAtivo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{curso}")
    public ResponseEntity<MatriculaResponseListAlunoDto> consultarCurso(@PathVariable String curso) {
        MatriculaResponseListAlunoDto dto = new MatriculaResponseListAlunoDto();

        dto.setCurso(cursoClient.buscarCursosPorNome(curso).getNome());
        dto.setProfessor(cursoClient.buscarCursosPorNome(curso).getProfessor());
        List<Matricula> matriculas = matriculaService.buscarMatriculasPorCursoId(cursoClient.buscarCursosPorNome(curso).getId());
        dto.addAlunos(matriculas.stream().map( x -> AlunoMapper.paraDto(alunoService.buscarPorId(x.getAlunoId()))).toList());
        dto.setTotalAlunos(dto.getAlunos().size());

        return ResponseEntity.ok(dto);
    }
}
