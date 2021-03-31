package br.unisul.aula;

import br.unisul.aula.dao.EpisodioDAO;
import br.unisul.aula.dao.SerieDAO;
import br.unisul.aula.dao.TemporadaDAO;
import br.unisul.aula.entidade.Episodio;
import br.unisul.aula.entidade.Serie;
import br.unisul.aula.entidade.Temporada;

public class App {

    public static void main(String[] args) {
        Serie serie1 = new Serie("Gothan", 2013, "História do Batman");
        Serie serie2 = new Serie("The Walking Dead", 2008, "Zumbi");
        Serie serie3 = new Serie("Flash", 2016, "História do Flash");

        Temporada tempGotham1 = new Temporada(1,"O início");
        tempGotham1.setSerie(serie1);
        Temporada tempGotham2 = new Temporada(2,"Continuidade");
        tempGotham1.setSerie(serie2);
        Temporada tempTWD1 = new Temporada(1,"O fim de tudo");
        tempTWD1.setSerie(serie2);
        Temporada tempTWD2 = new Temporada(2,"Guerras");
        tempTWD2.setSerie(serie2);
        Temporada tempTWD3 = new Temporada(1,"Novos desafios");
        tempTWD3.setSerie(serie2);
        Temporada tempFls1 = new Temporada(1,"O herói");
        tempFls1.setSerie(serie3);


        Episodio epGotham1 = new Episodio(1, "O iníco", "Primeiro capítulo");
        Episodio epGotham2 = new Episodio(2, "O segundo", "segundo capítulo");
        Episodio epGotham3 = new Episodio(1, "O terceiro", "terceiro capítulo");
        Episodio epGotham4 = new Episodio(2, "O quarto", "Quarto capítulo");

        Episodio epTWD1 = new Episodio(1, "O iníco", "Primeiro capítulo");
        Episodio epTWD2 = new Episodio(2, "O segundo", "segundo capítulo");
        Episodio epTWD3 = new Episodio(1, "O terceiro", "terceiro capítulo");

        Episodio epFLS1 = new Episodio(1, "O iníco", "Primeiro capítulo");
        Episodio epFLS2 = new Episodio(1, "O segundo", "segundo capítulo");

        SerieDAO serieDAO = new SerieDAO();
        serieDAO.persist(serie1);
        serieDAO.persist(serie2);
        serieDAO.persist(serie3);

        TemporadaDAO temporadaDAO =  new TemporadaDAO();
        temporadaDAO.persist(tempGotham1);
        temporadaDAO.persist(tempGotham2);
        temporadaDAO.persist(tempTWD1);
        temporadaDAO.persist(tempTWD2);
        temporadaDAO.persist(tempTWD3);
        temporadaDAO.persist(tempFls1);

        EpisodioDAO episodioDAO = new EpisodioDAO();
        episodioDAO.persist(epGotham1);
        episodioDAO.persist(epGotham2);
        episodioDAO.persist(epGotham3);
        episodioDAO.persist(epGotham4);
        episodioDAO.persist(epTWD1);
        episodioDAO.persist(epTWD2);
        episodioDAO.persist(epTWD3);
        episodioDAO.persist(epFLS1);
        episodioDAO.persist(epFLS2);



    }
}
