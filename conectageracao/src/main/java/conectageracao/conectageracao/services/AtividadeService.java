package conectageracao.conectageracao.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import conectageracao.conectageracao.entities.Atividade;
import conectageracao.conectageracao.entities.Atividade.StatusAtividade;
import conectageracao.conectageracao.entities.Pessoa;
import conectageracao.conectageracao.exceptions.AgendamentoInvalidoException;
import conectageracao.conectageracao.exceptions.AtividadeNaoEncontradaException;
import conectageracao.conectageracao.repositories.AtividadeRepositorio;
import conectageracao.conectageracao.repositories.PessoaRepositorio;

//Regras de negócio, testar 

@Service
public class AtividadeService {
    @Autowired
    private AtividadeRepositorio atividadeRepositorio;

    @Autowired
    private PessoaRepositorio pessoaRepositorio;

    // quero listar todas as atividades
    public List<Atividade> listarTodas() {
        return atividadeRepositorio.findAll();
    }

    // idoso quer criar uma atividade
    public Atividade criar(Atividade atividade) {
        return atividadeRepositorio.save(atividade);
    }

    // voluntário quer se inscrever em uma atividade
    public Atividade solicitarAgendamento(Long atividadeId, Long voluntarioId)
            throws AtividadeNaoEncontradaException, AgendamentoInvalidoException {

        Atividade atividade = atividadeRepositorio.findById(atividadeId)
                .orElseThrow(() -> new AtividadeNaoEncontradaException("Atividade não encontrada"));

        if (atividade.getStatus() != StatusAtividade.PENDENTE) {
            throw new AgendamentoInvalidoException("Atividade não está disponível para agendamento.");
        }

        Pessoa voluntario = new Pessoa();
        voluntario.setId(voluntarioId);
        atividade.setVoluntario(voluntario);

        atividade.setStatus(StatusAtividade.EM_ANDAMENTO);
        return atividadeRepositorio.save(atividade);
    }

    // quero buscar atividade por Id
    public Atividade buscarPorId(Long id) {
        // Busca uma atividade pelo ID, retornando null se não for encontrada
        return atividadeRepositorio.findById(id).orElse(null);
    }

    // quer remover uma atividade
    public String remover(Long id) {
        // Verifica se a atividade existe antes de remover
        Optional<Atividade> atividade = atividadeRepositorio.findById(id);
        if (atividade.isPresent()) {
            atividadeRepositorio.deleteById(id);
            return "Atividade removida com sucesso!";
        } else {
            return "Atividade não encontrada.";
        }
    }

    // idoso (ou todos) quer atualizar uma atividade
    public Atividade atualizar(Long id, Atividade atividade) throws AtividadeNaoEncontradaException {
        if (!atividadeRepositorio.existsById(id)) {
            throw new Error();
        }
        atividade.setId(id);
        return atividadeRepositorio.save(atividade);
    }

    // atribuir um voluntário a uma atividade e atualizar o status de uma atividade

    public Atividade atribuirVoluntario(Long atividadeId, Long voluntarioId) {
        Atividade atividade = atividadeRepositorio.findById(atividadeId).orElse(null);
        Pessoa voluntario = pessoaRepositorio.findById(voluntarioId).orElse(null);

        if (atividade != null && voluntario != null) {
            atividade.setVoluntario(voluntario); // Atribui voluntário encontrado à atividade
            atividade.setStatus(StatusAtividade.EM_ANDAMENTO); // Muda o status para EM_ANDAMENTO
            return atividadeRepositorio.save(atividade); // Salva atividade atualizada
        } else {
            return null;
        }
    }

    public Atividade atualizarStatus(Long atividadeId, StatusAtividade novoStatus)
            throws AtividadeNaoEncontradaException {
        Atividade atividade = atividadeRepositorio.findById(atividadeId).orElseThrow(
                () -> new AtividadeNaoEncontradaException("Atividade com ID " + atividadeId + " não encontrada."));
        atividade.setStatus(novoStatus);
        return atividadeRepositorio.save(atividade);
    }

    public List<Atividade> filtrarAtividades(String nome, String localizacao, String modo, StatusAtividade status,
            String tag) {
        return atividadeRepositorio.findAll().stream()
                .filter(atividade -> (nome == null || atividade.getNome().contains(nome)) &&
                        (localizacao == null || atividade.getLocalizacao().contains(localizacao)) &&
                        (modo == null || atividade.getModo().contains(modo)) &&
                        (status == null || atividade.getStatus() == status) &&
                        (tag == null || atividade.getTags().contains(tag)))
                .collect(Collectors.toList());
    }

    public List<Atividade> getHistoricoVoluntario(Long voluntarioId) {
        return atividadeRepositorio.findByVoluntarioId(voluntarioId);
    }

    public List<Atividade> getHistoricoIdoso(Long idosoId) {
        return atividadeRepositorio.findByIdosoId(idosoId);
    }
}