package conectageracao.conectageracao.controllers;

import conectageracao.conectageracao.entities.Atividade;
import conectageracao.conectageracao.services.AtividadeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/busca")
public class BuscaController {

    @Autowired
    private AtividadeService atividadeService;

    @GetMapping("/atividades")
    public List<Atividade> filtrarAtividades(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String localizacao,
            @RequestParam(required = false) String modo,
            @RequestParam(required = false) Atividade.StatusAtividade status,
            @RequestParam(required = false) String tag) {

        return atividadeService.filtrarAtividades(nome, localizacao, modo, status, tag);
    }
}