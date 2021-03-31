<%@ page import="br.unisul.aula.dao.SerieDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="br.unisul.aula.entidade.Serie" %><%--
  Created by IntelliJ IDEA.
  User: edson
  Date: 25/10/2018
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        response.setContentType("text/html;charset=UTF-8");
    %>
    <title>Title</title>
</head>
<body>
<h1>Listar</h1>
 <table>
     <thead>
     <tr>
         <th>Nome</th>
         <th>Ano crição</th>
         <th>Descrição</th>
         <th>Ações</th>
     </tr>
     </thead>
     <tbody>
     <%
         SerieDAO serieDAO = new SerieDAO();
         List<Serie> serieList = serieDAO.list();
         for (Serie serie: serieList){
     %>
     <tr>
         <td><%=serie.getNome()%></td>
         <td><%=serie.getAnoCriacao()%></td>
         <td><%=serie.getDescricao()%></td>
         <td><a href="/enviarSeriado?acao=alterar&id=<%=serie.getId()%>">Alterar</a> </td>
     </tr>
     <%
         }
     %>
     </tbody>
 </table>

</body>
</html>
