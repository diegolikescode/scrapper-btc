package br.unisul.aula.modelo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jpa_participantes")
public class Participante {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idParticipantes")
    private int idParticipante;
    @Column(name = "nmParticipante", nullable = false)
    private String nome;
    @Column(name = "nmCargo", length = 50, nullable = false)
    private String cargo;

    @ManyToMany
    private List<Projeto> projetos;

    public Participante(){
        this.projetos = new ArrayList<Projeto>();
    }

    public Participante(int idParticipante) {
        this.idParticipante=idParticipante;
    }

    public int getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(int idParticipante) {
        this.idParticipante = idParticipante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }
    public void setProjetos(Projeto projeto) {
        this.projetos.add(projeto);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Participante)) return false;

        Participante that = (Participante) o;

        return getIdParticipante() == that.getIdParticipante();
    }

    @Override
    public int hashCode() {
        return getIdParticipante();
    }
}
