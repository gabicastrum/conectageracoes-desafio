package conectageracao.conectageracao.controllers;

import conectageracao.conectageracao.entities.Atividade;
import conectageracao.conectageracao.entities.Atividade.StatusAtividade;
import conectageracao.conectageracao.exceptions.AtividadeNaoEncontradaException;
import conectageracao.conectageracao.services.AtividadeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/atividades")
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    @GetMapping
    public List<Atividade> listarTodas(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String localizacao,
            @RequestParam(required = false) String modo,
            @RequestParam(required = false) StatusAtividade status,
            @RequestParam(required = false) String tag

    ) {
        return atividadeService.filtrarAtividades(nome, localizacao, modo, status, tag);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atividade> buscarPorId(@PathVariable Long id) {
        Atividade atividade = atividadeService.buscarPorId(id);
        if (atividade != null) {
            return ResponseEntity.ok(atividade);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Atividade criar(@RequestBody Atividade atividade) {
        return atividadeService.criar(atividade);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remover(@PathVariable Long id) {
        String result = atividadeService.remover(id);
        if (result.equals("Atividade removida com sucesso!")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public Atividade atualizar(@PathVariable Long id, @RequestBody Atividade atividade)
            throws AtividadeNaoEncontradaException {
        return atividadeService.atualizar(id, atividade);
    }

    @PutMapping("/{atividadeId}/voluntario/{voluntarioId}")
    public Atividade atribuirVoluntario(@PathVariable Long atividadeId, @PathVariable Long voluntarioId) {
        return atividadeService.atribuirVoluntario(atividadeId, voluntarioId);
    }

    @PutMapping("/{atividadeId}/status")
    public Atividade atualizarStatus(@PathVariable Long atividadeId, @RequestParam StatusAtividade status)
            throws AtividadeNaoEncontradaException {
        return atividadeService.atualizarStatus(atividadeId, status);
    }

}