package br.ufac.sgcmapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AtendimentoTest {
    Atendimento a;
    @BeforeEach
    public void setUp(){
        a = new Atendimento();
    }


    @Test
    public void testAtendimentoId(){
        a.setId(3L);
        Long id = a.getId();
        assertEquals(3L, id);
    }

    @Test
    public void testAtendimentoData(){
        a.setData(LocalDate.now());
        LocalDate data =  a.getData();
        assertEquals(data, a.getData());
    }

    @Test
    public void testAtendimentoHora(){
        LocalTime hora = LocalTime.now();
        a.setHora(hora);
        assertEquals(hora, a.getHora());
    }
    
    @Test
    public void testAtendimentoProfissional(){
        Profissional p = new Profissional();
        a.setProfissional(p);
        assertEquals(p, a.getProfissional());
    }

    @Test
    public void testAtendimentoConvenio(){
        Convenio c = new Convenio();
        a.setConvenio(c);
        assertEquals(c, a.getConvenio());
    }

    @Test
    public void testAtencimentoPaciente(){
        Paciente paciente = new Paciente();
        a.setPaciente(paciente);
        assertEquals(paciente, a.getPaciente());
    }

    @Test
    public void testAtendimentoStatus(){
        assertEquals(EStatus.AGENDADO, a.getStatus());
    }
}
