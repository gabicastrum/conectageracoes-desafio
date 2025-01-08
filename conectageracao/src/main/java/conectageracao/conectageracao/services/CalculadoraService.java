package conectageracao.conectageracao.services;

public class CalculadoraService {

    // Método para somar dois números
    public double somar(double a, double b) {
        return a + b;
    }

    // Método para subtrair dois números
    public double subtrair(double a, double b) {
        return a - b;
    }

    // Método para multiplicar dois números
    public double multiplicar(double a, double b) {
        return a * b;
    }

    // Método para dividir dois números
    public double dividir(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Divisão por zero não é permitida.");
        }
        return a / b;
    }

    // Método para calcular a raiz quadrada de um número
    public double raizQuadrada(double a) {
        if (a < 0) {
            throw new ArithmeticException("Não é possível calcular a raiz quadrada de um número negativo.");
        }
        return Math.sqrt(a);
    }

    // Método para calcular o valor absoluto de um número
    public double valorAbsoluto(double a) {
        return Math.abs(a);
    }

    // Método para calcular a potência de um número
    public double potencia(double base, double expoente) {
        return Math.pow(base, expoente);
    }

    // Método para calcular o módulo (resto da divisão)
    public double modulo(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Divisão por zero não é permitida para calcular o módulo.");
        }
        return a % b;
    }

}