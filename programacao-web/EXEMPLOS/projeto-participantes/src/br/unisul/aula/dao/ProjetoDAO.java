package br.unisul.aula.dao;

import br.unisul.aula.modelo.Participante;
import br.unisul.aula.modelo.Projeto;

import java.util.List;

public interface ProjetoDAO {

    public boolean save(Projeto projeto);

    public boolean delete(int idProjeto);
    public Projeto findById(int idProjeto);
    public List<Projeto> findAll();

}
