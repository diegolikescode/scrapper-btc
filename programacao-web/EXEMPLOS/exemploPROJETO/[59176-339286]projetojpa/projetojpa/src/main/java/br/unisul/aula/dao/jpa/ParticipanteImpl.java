package br.unisul.aula.dao.jpa;

import br.unisul.aula.dao.JPAUtil;
import br.unisul.aula.dao.ParticipanteDAO;
import br.unisul.aula.modelo.Participante;

import javax.persistence.EntityManager;
import java.util.List;

public class ParticipanteImpl implements ParticipanteDAO {
    private EntityManager entityManager = JPAUtil.getEntityManager();
    @Override
    public boolean save(Participante participante) {

        entityManager.getTransaction().begin();
        entityManager.persist(participante);
        entityManager.getTransaction().commit();
        return false;
    }

    @Override
    public List<Participante> findAll() {
        return entityManager.createQuery("SELECT p from Participante p",
                Participante.class).getResultList();
    }

    @Override
    public boolean delete(int idParticipante) {
        entityManager.getTransaction().begin();
        entityManager.remove(new Participante(idParticipante));
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public void update(Participante participante){
        entityManager.getTransaction().begin();
        entityManager.merge(participante);
        entityManager.getTransaction().commit();
    }

    @Override
    public Participante findById(int idParticipante) {
        return entityManager.getReference(Participante.class,idParticipante);
    }
}
