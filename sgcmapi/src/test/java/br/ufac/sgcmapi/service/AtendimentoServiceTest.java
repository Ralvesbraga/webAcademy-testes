package br.ufac.sgcmapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.ufac.sgcmapi.model.Atendimento;
import br.ufac.sgcmapi.model.EStatus;
import br.ufac.sgcmapi.repository.AtendimentoRepository;
import jakarta.inject.Inject;

@ExtendWith(MockitoExtension.class)
public class AtendimentoServiceTest {
    @Mock
    AtendimentoRepository repo;

    @InjectMocks
    private AtendimentoService servico;

    Atendimento a1;
    Atendimento a2;
    List<Atendimento> lista;

    @BeforeEach
    public void setUp(){
        a1 = new Atendimento();
        a2 = new Atendimento();
        lista = new ArrayList<>();
        a1.setId(1L);
        a2.setStatus(EStatus.CONFIRMADO);
        lista.add(a1);
        lista.add(a2);
        
    }

    @Test
    public void testGetAll(){
        Mockito.when(repo.findAll()).thenReturn(lista);
        List<Atendimento> result = servico.get();
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(EStatus.CHEGADA, result.get(1).getStatus().proximo());
    }

}
