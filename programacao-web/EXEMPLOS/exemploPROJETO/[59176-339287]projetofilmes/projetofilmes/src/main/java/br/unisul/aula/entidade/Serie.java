package br.unisul.aula.entidade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "series")
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 25, nullable = false)
    private String nome;

    @Column(nullable = false, name = "anocriacao")
    private int anoCriacao;

    private String descricao;

    @OneToMany(mappedBy = "serie", orphanRemoval = true)
    private List<Temporada> temporadas;

    public Serie(){}

    public Serie(String nome, int anoCriacao, String descricao) {
        this.nome = nome;
        this.anoCriacao = anoCriacao;
        this.descricao = descricao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnoCriacao() {
        return anoCriacao;
    }

    public void setAnoCriacao(int anoCriacao) {
        this.anoCriacao = anoCriacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Temporada> getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(List<Temporada> temporadas) {
        this.temporadas = temporadas;
    }
}
