package conectageracao.conectageracao.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CalculadoraServiceTest {

    @Test
    public void somar() {
        CalculadoraService calculadora = new CalculadoraService();
        double resultado = calculadora.somar(15, 20);
        assertEquals(35, resultado);
    }

    @Test
    public void subtrair() {
        CalculadoraService calculadora = new CalculadoraService();
        double resultado = calculadora.subtrair(100, 20);
        assertEquals(80, resultado);
    }

    @Test
    public void multiplicar() {
        CalculadoraService calculadora = new CalculadoraService();
        double resultado = calculadora.multiplicar(5, 4);
        assertEquals(20, resultado);
    }

    @Test
    public void dividir() {
        CalculadoraService calculadora = new CalculadoraService();
        double resultado = calculadora.dividir(20, 4);
        assertEquals(5, resultado);
    }

    @Test
    public void dividirPorZero() {
        CalculadoraService calculadora = new CalculadoraService();
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculadora.dividir(10, 0);
        });
        assertEquals("Divisão por zero não é permitida.", exception.getMessage());
    }

    @Test
    public void raizQuadrada() {
        CalculadoraService calculadora = new CalculadoraService();
        double resultado = calculadora.raizQuadrada(16);
        assertEquals(4, resultado);
    }

    @Test
    public void raizQuadradaDeNumeroNegativo() {
        CalculadoraService calculadora = new CalculadoraService();
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculadora.raizQuadrada(-4);
        });
        assertEquals("Não é possível calcular a raiz quadrada de um número negativo.", exception.getMessage());
    }

    @Test
    public void valorAbsoluto() {
        CalculadoraService calculadora = new CalculadoraService();
        double resultado = calculadora.valorAbsoluto(-10);
        assertEquals(10, resultado);
    }

    @Test
    public void potencia() {
        CalculadoraService calculadora = new CalculadoraService();
        double resultado = calculadora.potencia(2, 3);
        assertEquals(8, resultado);
    }

    @Test
    public void modulo() {
        CalculadoraService calculadora = new CalculadoraService();
        double resultado = calculadora.modulo(10, 3);
        assertEquals(1, resultado);
    }

    @Test
    public void moduloComZero() {
        CalculadoraService calculadora = new CalculadoraService();
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculadora.modulo(10, 0);
        });
        assertEquals("Divisão por zero NÃO é permitida para calcular o módulo.", exception.getMessage());
    }

    @Test
    public void fatorial() {
        CalculadoraService calculadora = new CalculadoraService();
        double resultado = calculadora.fatorial(5);
        assertEquals(120, resultado);
    }

    @Test
    public void fatorialDeZero() {
        CalculadoraService calculadora = new CalculadoraService();
        double resultado = calculadora.fatorial(0);
        assertEquals(1, resultado);
    }
}