package br.ufac.sgcmapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Matches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
    @BeforeEach
    public void setUp(){
        a1= new Atendimento();
        a2 = new Atendimento();
        a1.setId(1L);
        Profissional p = new Profissional();
        p.setNome("Daniel");
        a1.setProfissional(p);
        lista = new ArrayList<>();
        lista.add(a1);
        lista.add(a2);
    }

    @Test
    public void testAtendimentoControllerGetAll() throws Exception{
        Mockito.when(servico.get()).thenReturn(lista);
        mock.perform(MockMvcRequestBuilders.get("/atendimento/")).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)));
    }

    @Test
    public void testAtendimentoControllerGetById() throws Exception{
        Mockito.when(servico.get(1L)).thenReturn(a1);
        mock.perform(MockMvcRequestBuilders.get("/atendimento/1")).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.profissional.nome", Matchers.is("Daniel")));
    }

}
