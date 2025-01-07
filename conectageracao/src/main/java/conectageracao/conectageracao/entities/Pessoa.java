package conectageracao.conectageracao.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(name = "EMAIL", length = 30, nullable = false)
    private String email;
    @Column(length = 30, nullable = false)
    private String senha;
    @Column(length = 50, nullable = false)
    private String endereco;
    @Column(nullable = false)
    private String papel;

    public Pessoa() {
    }

    public Pessoa(PessoaRequestDTO pessoaNova) {
        this.nome = pessoaNova.nome();
        this.email = pessoaNova.email();
        this.senha = pessoaNova.senha();
        this.endereco = pessoaNova.endereco();
        this.papel = pessoaNova.papel();

    }

    public Pessoa(String nome,String email,String senha,String endereco,String papel) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
        this.papel = papel;

    }




    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getPapel() {
        return papel;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(id, pessoa.id); // Comparação pelo ID
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // Hash code baseado no ID

    }
}