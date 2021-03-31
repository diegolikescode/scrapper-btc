<%@ page import="br.unisul.aula.modelo.Participante"%>
<%@ page import="br.unisul.aula.modelo.Projeto"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Sistema de Gerenciamento de Projetos</title>
</head>
<body>

	<%
		request.setCharacterEncoding("UTF-8");
		Participante participante = null;
		if (request.getAttribute("participante") != null) {
			participante = (Participante) request.getAttribute("participante");
		}
	%>
	<h1>Novo Participante</h1>
	<br />
	<form action="participanteServlet" method="POST">
		<input type="hidden" name="acao" value="salva" /> <input
			type="hidden" name="idParticipante"
			value='<%=request.getParameter("idParticipante") != null ? request.getParameter("idParticipante") : ""%>' />
		<br /> Nome: <input type="text" name="nome"
			value="<%=participante != null ? participante.getNome() : ""%>" /> <br />
		Cargo: <input type="text" name="cargo" size="8"
			value="<%=participante != null ? participante.getCargo() : ""%>" />
		<br />
		<%
			if (participante != null) {
		%>
		Projetos <select name="projetos" size="5" multiple>
			<%
				List<Projeto> projetos = (List) request.getAttribute("projetos");
					for (Projeto projeto : projetos) {
			%>
			<option value="<%=projeto.getIdProjeto()%>"
				<%if (participante.getProjetos().contains(projeto))
						out.print("selected");%>>
				<%=projeto.getDescricao()%>
			</option>
			<%
				}
			%>
		</select><br>
		<%
			}
		%>
		<p />
		&nbsp; 
			<input type="submit" value="Salvar">
</form>
	<br>
	<a href="index.jsp"><b>Menu</b></a>
</body>
</html>
