package com.matriculasapi.matriculas.web.controller;

import com.matriculasapi.matriculas.client.cursos.CursoClient;
import com.matriculasapi.matriculas.entity.Matricula;
import com.matriculasapi.matriculas.service.AlunoService;
import com.matriculasapi.matriculas.service.MatriculaService;
import com.matriculasapi.matriculas.web.dto.MatriculaCreateDto;
import com.matriculasapi.matriculas.web.dto.MatriculaResponseDto;
import com.matriculasapi.matriculas.web.dto.mapper.MatriculaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/matriculas")
@RestController
public class MatriculaController {

    private final MatriculaService matriculaService;
    private final CursoClient cursoClient;
    private final AlunoService alunoService;

    @PostMapping
    public ResponseEntity<MatriculaResponseDto> salvarMatricula(@RequestBody MatriculaCreateDto dto){

        Matricula matricula = matriculaService.salvar(dto.getCurso(), dto.getCpf());
        return ResponseEntity.status(HttpStatus.CREATED).body(MatriculaMapper.paraDto(matricula));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> alterarStatusAtivo(@PathVariable Long id){
        matriculaService.alterarStatusAtivo(id);
        return ResponseEntity.noContent().build();
    }
}
