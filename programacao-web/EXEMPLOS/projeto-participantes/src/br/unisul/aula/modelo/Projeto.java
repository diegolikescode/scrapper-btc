package br.unisul.aula.modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Projeto {
    private int idProjeto;
    private String descricao;
    private Date dataInicio;
    private Date dataFim;
    private int percentualConcluido;
    private String situacao;
    private List<Participante> participantes;

    public Projeto(){
        this.participantes = new ArrayList<Participante>();
    }

    public int getIdProjeto() {
        return this.idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
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
    public String getDataInicioFormatada(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(this.dataInicio);
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }
    public void setDataInicio(String dataInicio) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.dataInicio = format.parse(dataInicio);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Date getDataFim() {
        return dataFim;
    }
    public String getDataFimFormatada(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(this.dataFim);
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
    public void setDataFim(String dataFim) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.dataFim = format.parse(dataFim);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public int getPercentualConcluido() {
        return percentualConcluido;
    }

    public void setPercentualConcluido(int percentualConcluido) {
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
    public void setParticipantes(Participante  participante) {
        this.participantes.add(participante);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Projeto)) return false;

        Projeto projeto = (Projeto) o;

        return getIdProjeto() == projeto.getIdProjeto();
    }

    @Override
    public int hashCode() {
        return getIdProjeto();
    }
}
