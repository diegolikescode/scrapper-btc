package br.unisul.aula.repository;

import br.unisul.aula.modelo.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipanteRepositorio extends JpaRepository<Participante, Integer> {
}
