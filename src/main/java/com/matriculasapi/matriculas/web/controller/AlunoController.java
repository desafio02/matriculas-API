package com.matriculasapi.matriculas.web.controller;

import com.matriculasapi.matriculas.entity.Aluno;
import com.matriculasapi.matriculas.service.AlunoService;
import com.matriculasapi.matriculas.web.dto.AlunoCreateDto;
import com.matriculasapi.matriculas.web.dto.AlunoResponseDto;
import com.matriculasapi.matriculas.web.dto.mapper.AlunoMapper;
import com.matriculasapi.matriculas.web.exception.MensagemErro;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Alunos", description = "Contém todas as operações relativas aos recursos para cadastro e edição do status de um aluno")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    @Operation(summary = "Cadastrar um novo aluno",
            description = "Endpoint que cadastra um novo aluno.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aluno cadastrado com sucesso!",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlunoResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MensagemErro.class))),
            @ApiResponse(responseCode = "409", description = "Aluno com cpf já existente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MensagemErro.class)))
    })
    @PostMapping
    public ResponseEntity<AlunoResponseDto> salvar(@RequestBody @Valid AlunoCreateDto dto){

        Aluno aluno = alunoService.salvar(AlunoMapper.paraAluno(dto));

        return ResponseEntity.status(HttpStatus.CREATED).body(AlunoMapper.paraDto(aluno));
    }

    @Operation(summary = "Atualizar o status de um aluno",
            description = "Endpoint para atualizar o status de um aluno pelo cpf.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Status do aluno alterado com sucesso!",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Solicitação inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MensagemErro.class))),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MensagemErro.class)))
    })
    @PatchMapping("/{cpf}")
    public ResponseEntity<Void> alterarStatusAtivo(@PathVariable String cpf){
        alunoService.alterarStatusAtivo(cpf);
        return ResponseEntity.noContent().build();
    }

}
