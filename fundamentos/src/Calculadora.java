import java.math.BigDecimal;

public class Calculadora {
    public double soma(double x, double y){
        return x + y;
    }

    public double somaDecimal(double x, double y){
        BigDecimal soma = BigDecimal.valueOf(x).add(BigDecimal.valueOf(y));
        return soma.doubleValue();
    }

    public double div(double x, double y){
        if (y == 0) {
            throw new ArithmeticException(); 
        }
        return x / y;
    }
}
