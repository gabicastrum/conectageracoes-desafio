package conectageracao.conectageracao.controllers;

import conectageracao.conectageracao.services.CalculadoraService;

public class SalaDeAula {

    private final CalculadoraService calculadoraService;

    public SalaDeAula(CalculadoraService calculadoraService) {
        this.calculadoraService = calculadoraService;
    }

    public double calcularMedia(double nota1, double nota2) {
        validarNota(nota1);
        validarNota(nota2);

        var notaSomadas = calculadoraService.somar(nota1, nota2);
        return calculadoraService.dividir(notaSomadas, 2);
    }

    private void validarNota(double nota) {
        if (nota < 0) {
            throw new IllegalArgumentException("Nota {" + nota + "} inválida");
        }
    }

    public boolean verificarAprovacao(double nota, double notaCorte) {
        validarNota(nota);

        return calculadoraService.subtrair(nota, notaCorte) >= 0;
    }

}