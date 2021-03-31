package br.unisul.aula.dao;

import br.unisul.aula.entidade.Episodio;

import javax.persistence.EntityManager;
import java.util.List;

public class EpisodioDAO {
    public void persist (Episodio episodio){
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(episodio);
        em.getTransaction().commit();
    }

    public void remove(Episodio episodio){
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(episodio);
        em.getTransaction().commit();
    }

    public void update(Episodio episodio){
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.merge(episodio);
        em.getTransaction().commit();
    }

    public List<Episodio> list(){
        EntityManager em = JPAUtil.getEntityManager();

        return em.createQuery("SELECT t FROM Episodio t",
                Episodio.class).getResultList();
    }

    public Episodio get(long id){
        EntityManager em = JPAUtil.getEntityManager();
        return em.getReference(Episodio.class, id);
    }

}
