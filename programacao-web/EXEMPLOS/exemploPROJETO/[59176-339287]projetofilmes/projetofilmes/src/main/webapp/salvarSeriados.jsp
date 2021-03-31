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
    <title>Title</title>
</head>
<%
    String id="";
    String nome ="";
    String ano = "";
    String descricao = "";
    if (request.getAttribute("serie")!=null){
        Serie serie = (Serie) request.getAttribute("serie");
        id = serie.getId()+"";
        nome = serie.getNome();
        ano = serie.getAnoCriacao()+"";
        descricao = serie.getDescricao();
    }
%>
<body>
<h1>Incluir/Alterar</h1>

<form action="/enviarSeriado" method="post">
    <input type="hidden" name="hdnCodigo" value="<%=id%>">
    Nome <input type="text" name="txtNome" value="<%=nome%>"> <br>
    Ano Criação <input type="number" name="nrAno" value="<%=ano%>"> <br>
    Descrição <input type="text" name="txtDescricao" value="<%=descricao%>"> <br>

    <input type="submit" value="Enviar">
</form>
</body>
</html>
