package br.unisul.aula.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

    private static EntityManagerFactory entityManagerFactory;

    public static EntityManager getEntityManager(){
        if (entityManagerFactory==null){
            entityManagerFactory= Persistence.createEntityManagerFactory("persistenciaDisciplina");
        }
        return entityManagerFactory.createEntityManager();
    }
}
