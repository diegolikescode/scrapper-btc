package br.unisul.aula.repository;

import br.unisul.aula.modelo.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetoRepositorio extends JpaRepository<Projeto, Integer> {


    @Query("SELECT p from Projeto p where p.descricao like %:descricao%")
    List<Projeto> findAllByDescricaoContains(@Param("descricao") String descricao);


    @Query("SELECT p FROM Projeto p  INNER JOIN p.participantes part WHERE part.nome like %:nome%")
    List<Projeto > findAllByParticipantesContains(@Param("nome") String nome);

}
