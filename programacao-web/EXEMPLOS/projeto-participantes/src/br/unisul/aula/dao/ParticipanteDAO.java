package br.unisul.aula.dao;

import br.unisul.aula.modelo.Participante;

import java.util.List;

public interface ParticipanteDAO {
    public boolean save(Participante participante);
    public List<Participante> findAll();
    public boolean delete(int idParticipante);
    public Participante findById(int idParticipante);
}
