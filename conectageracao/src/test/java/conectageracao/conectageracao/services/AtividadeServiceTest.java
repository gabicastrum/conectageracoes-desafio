package conectageracao.conectageracao.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import conectageracao.conectageracao.entities.Atividade;
import conectageracao.conectageracao.entities.Atividade.StatusAtividade;
import conectageracao.conectageracao.entities.Pessoa;

import conectageracao.conectageracao.repositories.AtividadeRepositorio;
import conectageracao.conectageracao.repositories.PessoaRepositorio;

@ExtendWith(MockitoExtension.class)
public class AtividadeServiceTest {
    @Mock
    private AtividadeRepositorio atividadeRepositorio;

    @Mock
    private PessoaRepositorio pessoaRepositorio;

    @InjectMocks
    private AtividadeService atividadeService;

    private Atividade criarAtividadeTeste() {
        return new Atividade("Acompanhar em consulta médica",
                "Gostaria de uma acompanhante para a minha consulta ao ortopedista",
                Set.of("saúde", "consulta", "acompanhamento"), "Hospital das Clínicas", "Presencial", LocalDate.now(),
                new Pessoa(), null, Atividade.StatusAtividade.PENDENTE);
    }

    private Pessoa criarPessoaTeste() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Monchi Yoda");
        pessoa.setEmail("yoda@gmail.com");
        pessoa.setEndereco("Dagobah");
        return pessoa;

    }

    @Test
    public void listarTodas() {
        Atividade atividade = criarAtividadeTeste();
        when(atividadeRepositorio.findAll()).thenReturn(List.of(atividade));

        List<Atividade> atividades = atividadeService.listarTodas();

        assertEquals(1, atividades.size());
    }

    @Test
    public void buscarPorId() {
        Atividade atividade = criarAtividadeTeste();
        when(atividadeRepositorio.findById(1L)).thenReturn(java.util.Optional.of(atividade));

        Atividade atividadeEncontrada = atividadeService.buscarPorId(1L);

        assertEquals(atividade, atividadeEncontrada);
    }

    @Test
    public void criarNovaAtividade() {
        Atividade atividade = criarAtividadeTeste();
        when(atividadeRepositorio.save(atividade)).thenReturn(atividade);

        Atividade atividadeCriada = atividadeService.criar(atividade);

        assertEquals(atividade, atividadeCriada);
    }

    @Test
    public void atribuirVoluntario() {
        Atividade atividade = criarAtividadeTeste();
        Pessoa voluntario = criarPessoaTeste();

        when(atividadeRepositorio.findById(1L)).thenReturn(Optional.of(atividade));
        when(pessoaRepositorio.findById(1L)).thenReturn(Optional.of(voluntario));
        when(atividadeRepositorio.save(any(Atividade.class))).thenAnswer(i -> i.getArguments()[0]);

        Atividade atividadeAtualizada = atividadeService.atribuirVoluntario(1L, 1L);

        assertNotNull(atividadeAtualizada);
        assertEquals(voluntario, atividadeAtualizada.getVoluntario());
        assertEquals(StatusAtividade.EM_ANDAMENTO, atividadeAtualizada.getStatus());
    }
}