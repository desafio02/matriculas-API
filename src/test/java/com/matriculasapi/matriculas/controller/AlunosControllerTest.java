package com.matriculasapi.matriculas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matriculasapi.matriculas.entity.Aluno;
import com.matriculasapi.matriculas.exception.ExcecaoDadoDuplicado;
import com.matriculasapi.matriculas.service.AlunoService;
import com.matriculasapi.matriculas.web.controller.AlunoController;
import com.matriculasapi.matriculas.web.dto.AlunoCreateDto;
import com.matriculasapi.matriculas.web.dto.AlunoResponseDto;
import com.matriculasapi.matriculas.web.dto.mapper.AlunoMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDate;

import static com.matriculasapi.matriculas.common.entity.AlunoConstants.ALUNO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(AlunoController.class)
@ExtendWith(SpringExtension.class)
public class AlunosControllerTest {

    @MockBean
    private AlunoService alunoService;

    @MockBean
    private AlunoMapper alunoMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void salvarAluno_ComDadosValidos_RetornaAluno() throws Exception {
        Aluno aluno = new Aluno (
                1L,
                "Jalim Rabei",
                "09759576007",
                LocalDate.of(2024, 5, 11),
                true,
                Aluno.Sexo.M
        );

        when(alunoService.salvar(aluno)).thenReturn(aluno);

        mockMvc.perform(post("/api/v1/alunos")
                        .content(objectMapper.writeValueAsString(aluno))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value(aluno));
    }

    @Test
    public void salvarAluno_ComCpfExistente_RetornarConflito() throws Exception {
        when(alunoService.salvar(any())).thenThrow(ExcecaoDadoDuplicado.class);

        mockMvc
                .perform(
                        post("/api/v1/alunos").content(objectMapper.writeValueAsString(ALUNO))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());

    }
}
