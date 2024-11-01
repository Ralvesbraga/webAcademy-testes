import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.EmptyStackException;

import org.junit.Before;
import org.junit.Test;

public class PilhaTest {
    // Testar as operações tamanho, vazia, empilhar e desempilhar;
    Pilha<String> p;
    @Before
    public void Setup(){
        p = new Pilha<>();
    }

    @Test
    public void testSize(){
        assertEquals(0, p.size());
        p.push("1");
        assertEquals(1, p.size());
    }

    @Test
    public void testEmpty(){
        assertTrue(p.isEmpty());
    }

    @Test
    public void testPush(){
        p.push("2");
        p.push("3");
        assertEquals(2, p.size());
    }

    @Test
    public void testPop(){
        p.push("1");
        p.pop();
        assertEquals(0, p.size());
        assertThrows(EmptyStackException.class, () -> p.pop());
    }
}
