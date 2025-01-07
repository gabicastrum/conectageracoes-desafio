package conectageracao.conectageracao.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import conectageracao.conectageracao.entities.Atividade;
import conectageracao.conectageracao.services.AtividadeService;

@RestController
@RequestMapping("/historico")
public class HistoricoController {
    @Autowired
    private AtividadeService atividadeService;

    @GetMapping("/voluntario/{voluntarioId}")
    public List<Atividade> historicoVoluntario(@PathVariable Long voluntarioId) {
        return atividadeService.getHistoricoVoluntario(voluntarioId);

    }

    @GetMapping("/idoso/{idosoId}")
    public List<Atividade> historicoIdoso(@PathVariable Long idosoId) {
        return atividadeService.getHistoricoIdoso(idosoId);
    }
}