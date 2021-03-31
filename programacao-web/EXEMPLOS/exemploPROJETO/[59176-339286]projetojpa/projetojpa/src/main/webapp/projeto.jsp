<%@ page import="br.unisul.aula.modelo.Projeto" %>
<%@ page import="java.util.List" %>
<%@ page import="br.unisul.aula.modelo.Participante" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cadastrar Projeto</title>
</head>
<body>
<h1>Novo Projeto</h1> <br>
<%
    Projeto projeto = null;
    if (request.getAttribute("projeto") != null) {
        projeto = (Projeto) request.getAttribute("projeto");
    }
%>
<form action="projetoServlet">
    <input type="hidden" name="acao" value="salva"/>
    <input type="hidden" name="idProjeto"
           value="<%=request.getParameter("idProjeto")!=null?request.getParameter("idProjeto"):""%>">
    Descrição <input type="text" name="descProjeto" id="idProjeto"
                     value="<%=projeto!=null?projeto.getDescricao():""%>"/><br>
    Data Inicio:
    <input type="date" name="dataInicio" id="idInicio"
           value="<%=projeto!=null?projeto.getDataInicioFormatada():""%>"><br>
    Data Fim:
    <input type="date" name="dataFim" id="idFim"
           value="<%=projeto!=null?projeto.getDataFimFormatada():""%>"><br>
    Percentual Concluído: <input type="number" name="percConcluido" id="idPercConcluido"
                                 value="<%=projeto!=null?projeto.getPercentualConcluido():""%>"><br>

    Situação:<input type="text" name="situacao" id="idsituacao"
                    value="<%=projeto!=null?projeto.getSituacao():""%>"><br>
    <%
        if (projeto != null) {
            List<Participante> participantes = (List) request.getAttribute("participantes");
            if (participantes.size() > 0) {
    %>
    Participante:
    <select name="participantes" size="5" multiple>
        <%
            for (Participante participante : participantes) {

        %>
        <option value="<%=participante.getIdParticipante()%>"
                <%=participante.getProjetos().contains(projeto)?"selected":""%>>
            <%=participante.getNome()%>
        </option>
        <%
            }
        %>

        %>
    </select>
    <%
            }
        }
    %>

    <br>
    <input type="submit" value="Enviar">

</form>
<br>
<a href="index.jsp">Menu</a>

</body>
</html>
