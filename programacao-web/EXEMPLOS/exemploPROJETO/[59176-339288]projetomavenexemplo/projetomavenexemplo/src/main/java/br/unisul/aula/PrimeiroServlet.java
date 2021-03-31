package br.unisul.aula;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


@WebServlet(name = "PrimeiroServlet", urlPatterns = "/primeiro")
public class PrimeiroServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String mensagem = "Olá "+ nome + ", seja bem-vindo" +
                "a nossa página";
        request.setAttribute("mensagem",mensagem);
        JSONArray jsonArray = new JSONArray();
        for (int i=0;i<5;i++) {
            JSONObject jsonObject = new JSONObject();
            FileWriter fileWriter = null;
            jsonObject.put("nome","teste "+(i+1));
            jsonObject.put("descricao","incluído automáticamente");
            jsonArray.add(i, jsonObject);

        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dados", jsonArray);

        request.setAttribute("json",jsonObject.toJSONString());

        getServletContext().
                getRequestDispatcher("/index.jsp").
                forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("Hello Word");
    }
}
