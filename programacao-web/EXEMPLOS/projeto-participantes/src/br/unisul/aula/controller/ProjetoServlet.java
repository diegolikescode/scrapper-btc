package br.unisul.aula.controller;

import br.unisul.aula.dao.ParticipanteDAO;
import br.unisul.aula.dao.ProjetoDAO;
import br.unisul.aula.dao.mysql.ParticipanteDAOMySQL;
import br.unisul.aula.dao.mysql.ProjetoDAOMySQL;
import br.unisul.aula.dao.postgresql.ParticipanteDAOPostgreSQL;
import br.unisul.aula.dao.postgresql.ProjetoDAOPostgreSQL;
import br.unisul.aula.modelo.Projeto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@WebServlet(name = "ProjetoServlet",
        urlPatterns = "/projetoServlet")
public class ProjetoServlet extends HttpServlet {
    //private ProjetoDAO dao = new ProjetoDAOMySQL();
    private ProjetoDAO dao = new ProjetoDAOPostgreSQL();
//    private ParticipanteDAO participanteDAO = new ParticipanteDAOMySQL();
    private ParticipanteDAO participanteDAO = new ParticipanteDAOPostgreSQL();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");
        
         if (acao.equalsIgnoreCase("salva")){
             salvaProjeto(request, response);
             
         } else if (acao.equalsIgnoreCase("apaga")){
             apagaProjeto(request,response);
         } else if (acao.equalsIgnoreCase("lista")){
             listaProjeto(request,response);
         } else {
             editaProjeto(request,response);
         }
    }

    private void apagaProjeto(HttpServletRequest request, HttpServletResponse response) {

        try {
            dao.delete(Integer.parseInt(request.getParameter("idProjeto")));
            listaProjeto(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void editaProjeto(HttpServletRequest request, HttpServletResponse response) {
        Projeto projeto = null;
        try {
            projeto = dao.findById(Integer.parseInt(request.getParameter("idProjeto")));
            request.setAttribute("projeto", projeto);
            request.setAttribute("participantes", participanteDAO.findAll());
            request.getRequestDispatcher("/projeto.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }  catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void salvaProjeto(HttpServletRequest request, HttpServletResponse response) {

        try {
            Projeto projeto = preencherProjeto(request);
            dao.save(projeto);
            listaProjeto(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listaProjeto(HttpServletRequest request,
                              HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("projetos",dao.findAll());
        request.getRequestDispatcher("/projetos.jsp").forward(request,response);
    }

    private Projeto preencherProjeto(HttpServletRequest request) {
        String id = request.getParameter("idProjeto");
        String descProjeto = request.getParameter("descProjeto");
        String dataInicio = request.getParameter("dataInicio");
        String dataFim = request.getParameter("dataFim");
        int perConcluido = Integer.parseInt(request.getParameter("percConcluido"));
        String situacao = request.getParameter("situacao");
        String[] participantes = request.getParameterValues("participantes");
        Projeto projeto = new Projeto();
        if (participantes!=null){
            for(int i=0; i<participantes.length;i++) {
                projeto.setParticipantes(participanteDAO.findById(
                        Integer.parseInt(participantes[i])));
            }
        }
        if ((id!=null) && (!id.equalsIgnoreCase(""))){
            projeto.setIdProjeto(Integer.parseInt(id));
        }
        projeto.setDescricao(descProjeto);
        projeto.setDataInicio(dataInicio);
        projeto.setDataFim(dataFim);
        projeto.setPercentualConcluido(perConcluido);
        projeto.setSituacao(situacao);
        return projeto;

    }
}
