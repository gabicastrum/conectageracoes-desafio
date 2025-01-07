package conectageracao.conectageracao.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import conectageracao.conectageracao.entities.Pessoa;

import org.springframework.beans.factory.annotation.Autowired;

import conectageracao.conectageracao.repositories.PessoaRepositorio;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepositorio repositorio;

    @GetMapping("{id}")
    public Pessoa getPerfil(@PathVariable Long id) {
        return repositorio.findByid(id);
    }

    @PutMapping("{id}")
    public Pessoa editarPerfil(@PathVariable Long id, @RequestBody Pessoa pessoaEditada) {
        Pessoa pessoa = repositorio.findByid(pessoaEditada.getId());
        if (pessoa == null) {
            throw new RuntimeException("Usuário não encontrado!");
        }
        
        pessoa.setEndereco(pessoaEditada.getEndereco());
        pessoa.setPapel(pessoaEditada.getPapel());
        return repositorio.save(pessoa);
    }
}