package br.ufac.sgcmapi.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufac.sgcmapi.model.Atendimento;
import br.ufac.sgcmapi.model.Profissional;
import br.ufac.sgcmapi.service.AtendimentoService;

@WebMvcTest(AtendimentoController.class)
public class AtendimentoControllerTest {

    @MockBean
    private AtendimentoService servico;

    @Autowired
    private MockMvc mock;

    List<Atendimento> lista;
    Atendimento a1;
    Atendimento a2;
    String jsonContent;
    List<String> horarios;

    @BeforeEach
    public void setUp() throws JsonProcessingException{
        a1= new Atendimento();
        a2 = new Atendimento();
        lista = new ArrayList<>();
        horarios = new ArrayList<>();
        Profissional p = new Profissional();

        a1.setId(1L);
        p.setNome("Daniel");
        a1.setProfissional(p);
        // a1.setHora(LocalTime.of(10, 0,0));
        a2.setHora(LocalTime.of(11, 0,0));
        // horarios.add(a1.getHora().toString());
        horarios.add(a2.getHora().toString());
        jsonContent = new ObjectMapper().writeValueAsString(a1);
        lista.add(a1);
        lista.add(a2);
    }

    @Test
    public void testAtendimentoControllerGetAll() throws Exception{
        Mockito.when(servico.get()).thenReturn(lista);
        mock.perform(MockMvcRequestBuilders.get("/atendimento/"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)));
    }

    @Test
    public void testAtendimentoControllerGetById() throws Exception{
        Mockito.when(servico.get(1L)).thenReturn(a1);
        mock.perform(MockMvcRequestBuilders.get("/atendimento/1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.profissional.nome", Matchers.is("Daniel")));
    }

    @Test
    public void testAtendimentoControllerGetTermoBusca() throws Exception {
        Mockito.when(servico.get("termo")).thenReturn(lista);
        mock.perform(MockMvcRequestBuilders.get("/atendimento/busca/termo"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)));
    }


    @Test
    public void testAtendimentoControllerInsert() throws Exception{
        Mockito.when(servico.save(Mockito.any(Atendimento.class))).thenReturn(a1);
        mock.perform(MockMvcRequestBuilders.post("/atendimento/")
        .contentType(MediaType.APPLICATION_JSON).content(jsonContent))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));
    }

    @Test
    public void testAtendimentoControllerUpdate() throws Exception{
        Mockito.when(servico.save(Mockito.any(Atendimento.class))).thenReturn(a1);
        mock.perform(MockMvcRequestBuilders.put("/atendimento/")
        .contentType(MediaType.APPLICATION_JSON).content(jsonContent))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));
    }

    @Test
    public void testAtendimentoControllerDelete() throws Exception{
        mock.perform(MockMvcRequestBuilders.delete("/atendimento/1"))
        .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(servico).delete(1L);
        Mockito.verifyNoMoreInteractions(servico);
    }

    @Test
    public void testAtendimentoUpdateStatus() throws Exception{
        Mockito.when(servico.updateStatus(1L)).thenReturn(a1);
        mock.perform(MockMvcRequestBuilders.put("/atendimento/status/1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("AGENDADO")));
    }

    @Test
    public void testAtendimentoControllerGetHorarios() throws Exception{
        Mockito.when(servico.getHorarios(1L, LocalDate.now())).thenReturn(horarios);
        mock.perform(MockMvcRequestBuilders.get("/atendimento/horarios/1/2024-11-06"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0]", Matchers.is("11:00")));

    }
    
}
