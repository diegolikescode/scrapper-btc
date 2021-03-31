package br.unisul.aula.dao;

import br.unisul.aula.entidade.Temporada;

import javax.persistence.EntityManager;
import java.util.List;

public class TemporadaDAO {
    public void persist (Temporada temporada){
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(temporada);
        em.getTransaction().commit();
    }

    public void remove(Temporada temporada){
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(temporada);
        em.getTransaction().commit();
    }

    public void update(Temporada temporada){
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.merge(temporada);
        em.getTransaction().commit();
    }

    public List<Temporada> list(){
        EntityManager em = JPAUtil.getEntityManager();

        return em.createQuery("SELECT t FROM Temporada t", Temporada.class).getResultList();
    }

    public Temporada get(long id){
        EntityManager em = JPAUtil.getEntityManager();
        return em.getReference(Temporada.class, id);
    }

}
