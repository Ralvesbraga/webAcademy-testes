import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;

public class CalculadoraTeste {
    //Testes para as 4 operações:
    Calculadora c;
    @Before
    public void setUp(){
        c = new Calculadora();
    }
    @Test
    public void testSoma(){
        //Valor esperado
        //Valor atual
        assertEquals(0.82, c.soma(0.8,0.02), 0.000000000000001);
    }

    @Test
    public void testSomaDecimal(){
        assertEquals(0.82, c.somaDecimal(0.8, 0.02), 0.00000000000000001);
    }
    @Test
    public void testSub(){

    }
    @Test
    public void testMul(){

    }
    @Test
    public void testDiv(){
        assertEquals(2.5, c.div(5,2), 0.00000000000000001);
    }

    @Test
    public void testDivZero(){
        assertThrows(ArithmeticException.class, () -> c.div(5, 0));
    }
}
