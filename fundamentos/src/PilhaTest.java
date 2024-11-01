import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PilhaTest {
    // Testar as operações tamanho, vazia, empilhar e desempilhar;
    @Before
    Pilha p;
    public void Setup(){
        p = new Pilha();
    }

    @Test
    public void testSize(){
        assertEquals(0, p.size());
        p.push(item);
        assertEquals(1, p.size());
    }

    @Test
    public void testEmpty(){}

    @Test
    public void testPush(){}

    @Test
    public void testPop(){}
}
