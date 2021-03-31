package br.unisul.aula.modelo;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jpa_participantes")
public class Participante {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idParticipantes")
    private Integer idParticipante;
    @Column(name = "nmParticipante", nullable = false)
    private String nome;
    @Column(name = "nmCargo", length = 50, nullable = false)
    private String cargo;

    @ManyToMany(mappedBy = "participantes")
    @JsonIgnore
    private List<Projeto> projetos;

    public Participante(){
        this.projetos = new ArrayList<Projeto>();
    }

    public Participante(Integer idParticipante) {
        this.idParticipante=idParticipante;
    }

    public Integer getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(Integer idParticipante) {
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

    public void copyAttributes(Participante participanteNovo) {
        Field[] fieldsFromParticipanteOld = this.getClass().getDeclaredFields();
        Field[] fieldsFromParticipanteNew = participanteNovo.getClass().getDeclaredFields();

        for (Field currentfieldsFromParticipanteOld : fieldsFromParticipanteOld){
            for (Field currentfieldsFromParticipanteNew : fieldsFromParticipanteNew) {
                String nameOfTheFirstField = currentfieldsFromParticipanteOld.getName();
                String nameOfTheSecondField = currentfieldsFromParticipanteNew.getName();

                if (nameOfTheFirstField.equals(nameOfTheSecondField)) {
                    currentfieldsFromParticipanteOld.setAccessible(true);
                    currentfieldsFromParticipanteNew.setAccessible(true);

                    try {
                        currentfieldsFromParticipanteOld.set(this, currentfieldsFromParticipanteNew.get(participanteNovo));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
