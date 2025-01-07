package conectageracao.conectageracao.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import conectageracao.conectageracao.entities.Login;
import conectageracao.conectageracao.entities.Pessoa;
import conectageracao.conectageracao.repositories.PessoaRepositorio;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/login")
public class LoginController {

    final PessoaRepositorio repositorio;

    public LoginController(PessoaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @PostMapping
    public Long login(@RequestBody Login log) {
        Pessoa pessoa = repositorio.findByemail(log.getEmail());
        if (pessoa == null || !pessoa.getSenha().equals(log.getSenha())) { // Corrigido: Verifica a senha
            throw new RuntimeException("Usuário ou senha incorretos!");
        }
        return pessoa.getId();
    }
}