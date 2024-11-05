package br.ufac.sgcmapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.ufac.sgcmapi.model.Atendimento;
import br.ufac.sgcmapi.model.EStatus;
import br.ufac.sgcmapi.model.Profissional;
import br.ufac.sgcmapi.repository.AtendimentoRepository;

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
        a1.setHora(LocalTime.of(8, 0));
        a2.setHora(LocalTime.of(9, 0));
        lista.add(a1);
        lista.add(a2);
        
    }

    @Test
    public void testAtendimentoGetAll(){
        Mockito.when(repo.findAll()).thenReturn(lista);
        List<Atendimento> result = servico.get();
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(EStatus.CHEGADA, result.get(1).getStatus().proximo());
    }

    @Test
    public void testAtendimentoGetById(){
        Mockito.when(repo.findById(1L)).thenReturn(Optional.of(a1));
        Atendimento result = servico.get(1L);
        assertEquals(1L, result.getId());
        assertEquals(a1, result);
    }

    @Test
    public void testAtendimentoTermoBusca(){
        Mockito.when(repo.busca("termo")).thenReturn(lista);
        List<Atendimento> result = servico.get("termo");
        assertEquals(2, result.size());
        assertEquals(a2, result.get(1));
    }

    @Test
    public void testAtendimentoSave(){
        Mockito.when(repo.save(a2)).thenReturn(a2);
        Atendimento result = servico.save(a2);
        assertEquals(EStatus.CONFIRMADO, result.getStatus());
    }

    @Test
    public void testAtendimentoDelete(){
        Mockito.when(repo.findById(2L)).thenReturn(Optional.of(a1));
        servico.delete(2L);
        assertEquals(EStatus.CANCELADO, a1.getStatus());
        Mockito.verify(repo).save(a1);
    }

    @Test
    public void testAtendimentoUpdateStatus(){
        Mockito.when(repo.findById(1L)).thenReturn(Optional.of(a1));
        Atendimento result = servico.updateStatus(1L);
        assertEquals(EStatus.CONFIRMADO, result.getStatus());
        Mockito.verify(repo).save(a1);
    }

    @Test
    public void testAtendimentoGetHorarios(){
        Mockito.when(repo.findByProfissionalAndDataAndStatusNot(
            Mockito.any(Profissional.class), 
            Mockito.eq(LocalDate.now()), 
            Mockito.eq(EStatus.CANCELADO)))
            .thenReturn(lista);
        List<String> result = servico.getHorarios(1L, LocalDate.now());
        assertEquals(2, result.size());
        assertEquals("08:00:00", result.get(0));
        assertEquals("09:00:00", result.get(1));
        
    }

}
