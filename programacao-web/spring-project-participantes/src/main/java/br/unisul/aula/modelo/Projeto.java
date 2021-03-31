package br.unisul.aula.modelo;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "jpa_projetos")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idProjetos")
    private Integer idProjeto;

    @Column(name = "nmDescricao", nullable = false)
    private String descricao;
    @Column(name = "dtInicio")
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataInicio;
    @Column(name = "dtFinal")
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataFim;
    @Column(name = "nrPercConcluido")
    private Integer percentualConcluido;
    @Column(name = "nmSituacao")
    private String situacao;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="jpa_participantes_jpa_projetos",
            joinColumns={@JoinColumn(name="projetos_idProjetos")},
            inverseJoinColumns={@JoinColumn(name="Participante_idParticipantes")})
    private List<Participante> participantes;

    public Projeto(){

        this.participantes = new ArrayList<Participante>();
    }

    public Projeto(Integer idProjeto) {
        this.idProjeto=idProjeto;
    }

    public Integer getIdProjeto() {
        return this.idProjeto;
    }

    public void setIdProjeto(Integer idProjeto) {
        this.idProjeto = idProjeto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public Date getDataInicio() {
        return dataInicio;
    }
    @JsonIgnore
    public String getDataInicioFormatada(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(this.dataInicio);
    }


    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        System.out.println("teste"+dataInicio);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.dataInicio = format.parse(dataInicio);
        } catch (ParseException e) {
            System.out.println("Erro");
            e.printStackTrace();
        }
    }


    public Date getDataFim() {
        return dataFim;
    }

    @JsonIgnore
    public String getDataFimFormatada(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(this.dataFim);
    }


    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
    public void setDataFim(String dataFim) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.dataFim = format.parse(dataFim);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Integer getPercentualConcluido() {
        return percentualConcluido;
    }

    public void setPercentualConcluido(Integer percentualConcluido) {
        this.percentualConcluido = percentualConcluido;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }


    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Projeto)) return false;

        Projeto projeto = (Projeto) o;

        return getIdProjeto() == projeto.getIdProjeto();
    }

    public void copyAttributes (Projeto projeto){
        Field[] fieldsFromProjetoOld = this.getClass().getDeclaredFields();
        Field[] fieldsFromProjetoNew = projeto.getClass().getDeclaredFields();

        for (Field currentfieldsFromProjetoOld : fieldsFromProjetoOld){
            for (Field currentfieldsFromProjetoNew : fieldsFromProjetoNew) {
                String nameOfTheFirstField = currentfieldsFromProjetoOld.getName();
                String nameOfTheSecondField = currentfieldsFromProjetoNew.getName();

                if (nameOfTheFirstField.equals(nameOfTheSecondField)) {
                    currentfieldsFromProjetoOld.setAccessible(true);
                    currentfieldsFromProjetoNew.setAccessible(true);

                    try {
                        currentfieldsFromProjetoOld.set(this, currentfieldsFromProjetoNew.get(projeto));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }


}
