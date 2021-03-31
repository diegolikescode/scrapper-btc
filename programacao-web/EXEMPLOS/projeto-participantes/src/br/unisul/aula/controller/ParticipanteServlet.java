package br.unisul.aula.controller;

import br.unisul.aula.dao.ParticipanteDAO;
import br.unisul.aula.dao.ProjetoDAO;
import br.unisul.aula.dao.postgresql.ParticipanteDAOPostgreSQL;
import br.unisul.aula.dao.postgresql.ProjetoDAOPostgreSQL;
import br.unisul.aula.modelo.Participante;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@WebServlet(name = "ParticipanteServlet", urlPatterns = "/participanteServlet")
public class ParticipanteServlet extends HttpServlet {
    //private ProjetoDAO dao = new ProjetoDAOMySQL();
    private ParticipanteDAO dao = new ParticipanteDAOPostgreSQL();
    //    private ParticipanteDAO participanteDAO = new ParticipanteDAOMySQL();
    private ProjetoDAO daoProjeto = new ProjetoDAOPostgreSQL();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String acao = request.getParameter("acao");

        if (acao.equals("salva")) {
            salvaParticipante(request, response);
        } else if (acao.equals("apaga")) {
            apagaParticipante(request, response);
        } else if (acao.equals("lista")) {
            listaParticipante(request, response);
        } else {
            editaParticipante(request, response);
        }
    }

    public void salvaParticipante(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Participante participante = preencheParticipante(request);
            dao.save(participante);
            listaParticipante(request, response);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void apagaParticipante(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dao.delete(Integer.parseInt(request.getParameter("idParticipante")));
        listaParticipante(request, response);
    }

    public void editaParticipante(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Participante participante = dao.findById(Integer.parseInt(request.getParameter("idParticipante")));
        request.setAttribute("participante", participante);
        request.setAttribute("projetos", daoProjeto.findAll());
        request.getRequestDispatcher("/participante.jsp").forward(request, response);
    }

    public void listaParticipante(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("participantes", dao.findAll());
        request.getRequestDispatcher("/participantes.jsp").forward(request, response);
    }

    private Participante preencheParticipante(HttpServletRequest request) throws ParseException {
        String id = request.getParameter("idParticipante");
        String nome = request.getParameter("nome");
        String cargo = request.getParameter("cargo");
        String[] projetos = request.getParameterValues("projetos");
        Participante participante = new Participante();
        if (projetos != null) {
            for (int i = 0; i < projetos.length; i++) {
                participante.setProjetos(daoProjeto.findById(Integer.parseInt(projetos[i])));
            }
        }
        if ((id != null) && (!id.equalsIgnoreCase(""))) {
            participante.setIdParticipante(Integer.parseInt(id));
        }
        participante.setNome(nome);
        participante.setCargo(cargo);
        return participante;
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
