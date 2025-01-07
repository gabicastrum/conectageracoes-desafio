package conectageracao.conectageracao.entities;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "atividades")
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(length = 1000) // Limita o tamanho da descrição no banco de dados.
    private String descricao;

    @ElementCollection
    @CollectionTable(name = "atividades_tags", joinColumns = @jakarta.persistence.JoinColumn(name = "atividade_id"))
    @Column(name = "tag")
    private Set<String> tags;

    @Column(nullable = false)
    private String localizacao;

    @Column(nullable = false)
    private String modo;

    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "idoso_id")
    private Pessoa idoso; // idoso que criou a atividade

    @ManyToOne
    @JoinColumn(name = "voluntario_id")
    private Pessoa voluntario; // voluntario que se inscreveu na atividade

    @Enumerated(EnumType.STRING) // Armazena o status como string no banco
    @Column(nullable = false)
    private StatusAtividade status = StatusAtividade.PENDENTE; // status inicial da atividade

    // enum para representar o status da atividade
    public enum StatusAtividade {
        PENDENTE, EM_ANDAMENTO, CONCLUIDA
    }

    // construtores

    public Atividade() {
    }

    public Atividade(String nome, String descricao, Set<String> tags, String localizacao, String modo, LocalDate data,
            Pessoa idoso, StatusAtividade status, StatusAtividade pendente) {
        this.nome = nome;
        this.descricao = descricao;
        this.tags = tags;
        this.localizacao = localizacao;
        this.modo = modo;
        this.data = data;
        this.idoso = idoso;
        this.status = StatusAtividade.PENDENTE;
        this.voluntario = null;
    }

    public StatusAtividade getStatus() {
        return status;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Set<String> getTags() {
        return tags;
    }

    public Set<String> getNecessidades() {
        return tags;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public String getModo() {
        return modo;
    }

    public LocalDate getData() {
        return data;
    }

    public Pessoa getIdoso() {
        return idoso;
    }

    public Pessoa getVoluntario() {
        return voluntario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setIdoso(Pessoa idoso) {
        this.idoso = idoso;
    }

    public void setId(long id) {
        this.id = (long) id;
    }

    public void setVoluntario(Pessoa voluntario) {
        this.voluntario = voluntario;
    }

    public void setStatus(StatusAtividade status) {
        this.status = status;
    }

    // equals e hashcode
    // essas sobreposições garantem que a comparação de igualdade e o cálculo do
    // hash code sejam baseados no ID da entidade, o que é crucial para o
    // funcionamento correto com JPA e coleções.

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Atividade atividade = (Atividade) o;
        return Objects.equals(id, atividade.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}