package br.unisul.aula.servlet;

import br.unisul.aula.dao.SerieDAO;
import br.unisul.aula.entidade.Serie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SeriadoServlet", urlPatterns = "/enviarSeriado")
public class SeriadoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String nome = request.getParameter("txtNome");
        int ano = Integer.parseInt(request.getParameter("nrAno"));
        String descricao = request.getParameter("txtDescricao");
        int codigo;
        try {
            codigo = Integer.parseInt(request.getParameter("hdnCodigo"));

        }catch (NumberFormatException e){
            System.out.println( "Erro na conversão");
            codigo=0;
        }

        Serie serie;
        SerieDAO serieDAO = new SerieDAO();
        if (codigo>0){
            serie = serieDAO.get(codigo);
            serie.setNome(nome);
            serie.setAnoCriacao(ano);
            serie.setDescricao(descricao);
            serieDAO.update(serie);
        } else {
            serie = new Serie(nome, ano, descricao);
            serieDAO.persist(serie);
        }
        listarSeries(request,response);
        getServletContext().
                getRequestDispatcher("/listarSeriados.jsp").
                forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");
        String id = request.getParameter("id");
        response.setContentType("text/html;charset=UTF-8");
        if (acao.equalsIgnoreCase("alterar")){
            if (id!=null){
                SerieDAO serieDAO = new SerieDAO();
                Serie serie =  serieDAO.get(Integer.parseInt(id));
                request.setAttribute("serie", serie);
                getServletContext().getRequestDispatcher("/salvarSeriados.jsp").forward(request,response);
            }
        } else {
            request.setAttribute("serie", null);
            getServletContext().getRequestDispatcher("/salvarSeriados.jsp").forward(request,response);
        }
    }

    private void listarSeries(HttpServletRequest request, HttpServletResponse response){
        SerieDAO serieDAO = new SerieDAO();
        List<Serie> serieList = serieDAO.list();
        request.setAttribute("listaSerie", serieList);
    }
}
