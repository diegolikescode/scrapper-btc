package br.unisul.aula;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PessoaServlet", urlPatterns = "/form")
public class PessoaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("txtNome");
        String idade = request.getParameter("nrIdade");
        Pessoa pessoa = new Pessoa(nome, idade);
        ListaPessoas db = ListaPessoas.getInstance();
        db.incluir(pessoa);
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Formulário</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <form method=\"post\" action=\"/form\">\n" +
                "        Nome: <input type=\"text\" name=\"txtNome\">\n" +
                "        <br>\n" +
                "        Idade: <input type=\"number\" name=\"nrIdade\" min=\"0\" max=\"120\">\n" +
                "        <br>\n" +
                "        <input type=\"submit\" value=\"Cadastrar\">\n" +
                "    </form>\n" +
                "<table>" +
                "<tr><th>Nome</th><th>idade</th></tr>");
        for (Pessoa pess: db.listarTodas()) {
            out.println("<tr><td>"+pess.getNome()+
                    "</td><td>"+ pess.getIdade()+
                    "</td></tr>");
        }
                out.println("</table>"+
                "</body>\n" +
                "</html>");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
