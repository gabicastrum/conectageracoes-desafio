package conectageracao.conectageracao.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void raizQuadrada() {
        CalculadoraService calculadora = new CalculadoraService();
        double resultado = calculadora.raizQuadrada(16);
        assertEquals(4, resultado);
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
}