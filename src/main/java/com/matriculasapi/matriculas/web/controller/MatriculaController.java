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

import java.util.List;

@Tag(name = "Matriculas", description = "Contém todas as operações relativas aos recursos para cadastro, edição e leitura de uma matricula")
@RequiredArgsConstructor
@RequestMapping("/api/v1/matriculas")
@RestController
public class MatriculaController {

    private final MatriculaService matriculaService;
    private final CursoClient cursoClient;
    private final AlunoService alunoService;

    @Operation(summary = "Cadastrar uma nova matricula",
            description = "Endpoint que cadastra uma nova matricula.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Matricula cadastrada com sucesso!",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MatriculaResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MensagemErro.class))),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MensagemErro.class))),
            @ApiResponse(responseCode = "409", description = "Aluno já cadastrado no curso informado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MensagemErro.class)))
    })
    @PostMapping
    public ResponseEntity<MatriculaResponseDto> salvarMatricula(@RequestBody @Valid MatriculaCreateDto dto){
        Matricula matricula = matriculaService.salvar(MatriculaMapper.paraMatricula(dto, alunoService, cursoClient));
        return ResponseEntity.status(HttpStatus.CREATED).body(MatriculaMapper.paraDto(matricula, alunoService, cursoClient));
    }

    @Operation(summary = "Atualizar o status de uma matricula",
            description = "Endpoint para atualizar o status de uma matricula pelo id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Status da matricula alterada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Solicitação inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MensagemErro.class))),
            @ApiResponse(responseCode = "404", description = "Matricula não encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MensagemErro.class)))
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Void> alterarStatusAtivo(@PathVariable Long id){
        matriculaService.alterarStatusAtivo(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar um curso pelo nome",
            description = "Enpoint para buscar um curso pelo nome")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = MatriculaResponseListAlunoDto.class))),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MensagemErro.class)))
    })
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
