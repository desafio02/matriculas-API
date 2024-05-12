package com.matriculasapi.matriculas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matriculasapi.matriculas.entity.Aluno;
import com.matriculasapi.matriculas.service.AlunoService;
import com.matriculasapi.matriculas.web.controller.AlunoController;
import com.matriculasapi.matriculas.web.dto.AlunoResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AlunoController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class AlunosControllerTest {

    @MockBean
    private AlunoService alunoService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void salvarAluno_ComDadosValidos_RetornaAlunoResponseDto() throws Exception {
        Aluno aluno = new Aluno (
                1L,
                "Sand",
                "09759576007",
                LocalDate.of(2024, 5, 11),
                true,
                Aluno.Sexo.M
        );

        AlunoResponseDto responseDto = new AlunoResponseDto(
                "Sand",
                "M",
                true
        );

        given(alunoService.salvar(ArgumentMatchers.any())).willAnswer(invocation -> invocation.getArgument(0));

        ResultActions response= mockMvc.perform(post("/api/v1/alunos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(responseDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }
}