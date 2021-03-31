<%@ page import="java.util.List"%>
<%@ page import="br.unisul.aula.modelo.Projeto"%>
<%@ page import="br.unisul.aula.dao.ProjetoDAO"%>
<%@ page import="br.unisul.aula.dao.mysql.ProjetoDAOMySQL"%>
<%@ page import="br.unisul.aula.controller.ProjetoServlet"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Listar Projetos</title>
</head>
<body>
	<h1>Listar Projetos</h1>
	<table>
		<%
			int i = 0;
			List<Projeto> projetos = null;
			if (request.getAttribute("projetos") != null) {

				projetos = (List) request.getAttribute("projetos");
			} else {
				ProjetoServlet servlet = new ProjetoServlet();
				request.setAttribute("acao", "lista");
				servlet.listaProjeto(request, response);
			}
			for (Projeto projeto : projetos) {
		%>
		<tr>
			<td>Projeto#<%=++i%>: <%=projeto.getDescricao()%> | <a
				href="projetoServlet?acao=edita&idProjeto=<%=projeto.getIdProjeto()%>">Editar</a>
				| <a
				href="projetoServlet?acao=apaga&idProjeto=<%=projeto.getIdProjeto()%>">Apagar</a>

			</td>
		</tr>
		<%
			}
		%>
	</table>

	<br>
	<a href="index.jsp">Menu</a>
</body>
</html>
