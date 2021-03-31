<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ include file="cabecalho.jsp"%>
    <p>
        As informações cadastradas são:<br>
        Nome: <%= request.getParameter("txtNome")%>
        <br>
        <%
            SimpleDateFormat formatoSaida = new SimpleDateFormat("dd/MM/yyyy");
            Date dataNascimento =
                    new SimpleDateFormat("yyyy-mm-dd")
                            .parse(request.getParameter("dtNascimento"));
        %>
        Data Nascimento:<%=formatoSaida.format(dataNascimento)%>
        <br>
        <%@ include file="rodape.jsp"%>
    </p>
</body>
</html>
