<%@ page import="br.unisul.aula.modelo.Participante"%>
<%@ page import="java.util.List"%>
<%@ page import="br.unisul.aula.dao.ParticipanteDAO"%>
<%@ page import="br.unisul.aula.controller.ParticipanteServlet"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Sistema de Gerenciamento de Projetos</title>
</head>
<body>
	<table>
		<td><h1>Participantes cadastrados</h1></td>
		<%
			int i = 0;
			List<Participante> participantes = null;
			if (request.getAttribute("participantes") != null) {
				participantes = (List) request.getAttribute("participantes");
			} else {
				ParticipanteServlet servlet = new ParticipanteServlet();
				request.setAttribute("acao", "lista");
				servlet.listaParticipante(request, response);
			}

			for (Participante participante : participantes) {
		%>
		<tr>
			<td>Participante#<%=++i%>: <%=participante.getNome()%> | <a
				href='participanteServlet?acao=edita&idParticipante=<%=participante.getIdParticipante()%>'>
					Editar</a> | <a
				href='participanteServlet?acao=apaga&idParticipante=<%=participante.getIdParticipante()%>'>Apagar</a>
			</td>
		</tr>
		<%
			}
		%>
	</table>
	<br>
	<a href="index.jsp"><b>Menu</b></a>
</body>
</html>
