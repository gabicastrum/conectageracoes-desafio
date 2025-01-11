package conectageracao.conectageracao.services;

import conectageracao.conectageracao.SalaDeAula;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SalaDeAulaTest {
    @Mock
    private CalculadoraService calculadoraService;

    @InjectMocks
    private SalaDeAula salaDeAula;

    @Test
    void calcularMediaComNotasValidas() {
        double nota1 = 8.0;
        double nota2 = 7.0;

        when(calculadoraService.somar(nota1, nota2)).thenReturn(15.0);
        when(calculadoraService.dividir(15.0, 2)).thenReturn(7.5);

        double media = salaDeAula.calcularMedia(nota1, nota2);

        assertEquals(7.5, media);
    }

    @Test
    void calcularMediaComNotasInvalidas() {
        double nota1 = -3.0;
        double nota2 = 8.0;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            salaDeAula.calcularMedia(nota1, nota2);
        });

        assertEquals("Nota {-3.0} inválida", exception.getMessage());
    }

    @Test
    void verificarAprovacaoComNotaIgualOuAcimaDaCorte() {
        double nota = 7.0;
        double notaCorte = 5.0;

        when(calculadoraService.subtrair(nota, notaCorte)).thenReturn(2.0);

        boolean aprovado = salaDeAula.verificarAprovacao(nota, notaCorte);

        assertEquals(true, aprovado);
    }

    @Test
    void verificarAprovacaoComNotaAbaixoDaCorte() {
        double nota = 4.0;
        double notaCorte = 5.0;

        when(calculadoraService.subtrair(nota, notaCorte)).thenReturn(-1.0);

        boolean reprovado = salaDeAula.verificarAprovacao(nota, notaCorte);

        assertEquals(false, reprovado);
    }

    @Test
    void verificarAprovacaoComNotaInvalida() {
        double nota = -1.0;
        double notaCorte = 5.0;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            salaDeAula.verificarAprovacao(nota, notaCorte);
        });

        assertEquals("Nota {-1.0} inválida", exception.getMessage());
    }

    @Test
    void verificarSeDoisMaisDoisIgual4() {
        assertEquals(4, 2+2);
    }
}
