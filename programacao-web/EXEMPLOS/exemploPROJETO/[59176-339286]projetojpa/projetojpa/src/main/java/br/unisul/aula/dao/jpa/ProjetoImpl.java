package br.unisul.aula.dao.jpa;

import br.unisul.aula.dao.JPAUtil;
import br.unisul.aula.dao.ProjetoDAO;
import br.unisul.aula.modelo.Projeto;

import javax.persistence.EntityManager;
import java.util.List;

public class ProjetoImpl implements ProjetoDAO {
    private EntityManager entityManager = JPAUtil.getEntityManager();
    @Override
    public boolean save(Projeto projeto) {
        entityManager.getTransaction().begin();
        entityManager.persist(projeto);
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(int idProjeto) {
        entityManager.getTransaction().begin();
        entityManager.remove(new Projeto(idProjeto));
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public Projeto findById(int idProjeto) {
        return entityManager.find(Projeto.class, idProjeto);
    }

    @Override
    public List<Projeto> findAll() {
        return entityManager.createQuery("select p from Projeto p",Projeto.class).getResultList();
    }

    @Override
    public void update(Projeto projeto) {

    }
}
