package com.matriculasapi.matriculas.web.controller;

import com.matriculasapi.matriculas.entity.Aluno;
import com.matriculasapi.matriculas.service.AlunoService;
import com.matriculasapi.matriculas.web.dto.AlunoCreateDto;
import com.matriculasapi.matriculas.web.dto.AlunoResponseDto;
import com.matriculasapi.matriculas.web.dto.mapper.AlunoMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Alunos", description = "Contém todas as operações relativas aos recursos para cadastro, edição e leitura de um aluno")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/alunos")
public class AlunoController {

    private final AlunoService alunoService;
    @PostMapping
    public ResponseEntity<AlunoResponseDto> salvar(@RequestBody @Valid AlunoCreateDto dto){

        Aluno aluno = alunoService.salvar(AlunoMapper.paraAluno(dto));

        return ResponseEntity.status(HttpStatus.CREATED).body(AlunoMapper.paraDto(aluno));
    }

    @PatchMapping("/{cpf}")
    public ResponseEntity<Void> alterarStatusAtivo(@PathVariable String cpf){
        alunoService.alterarStatusAtivo(cpf);
        return ResponseEntity.noContent().build();
    }

}
