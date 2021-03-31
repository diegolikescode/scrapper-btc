<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="pessoa"
             class="br.unisul.aula.Personalidade"
             scope="session">
    <jsp:setProperty name="pessoa" property="cor"
                     param="txtCor"/>
</jsp:useBean>
<html>
<head>
    <title>Resultado</title>
</head>
<body>
Nome:
<jsp:getProperty name="pessoa" property="nome"/>
<br>
Idade:
<jsp:getProperty name="pessoa" property="idade"/>
<br>
<jsp:setProperty name="pessoa" property="cor" param="txtCor"/>
Cor:
<jsp:getProperty name="pessoa" property="cor"/>
<%
    if (pessoa.getCor().equalsIgnoreCase("azul")) {
        for (int i = 0; i < 10; i++) {
%>
<h1>É Avaiano</h1>
<%
        }
    } else {
        if (pessoa.getCor().equalsIgnoreCase("branco")) {
%>
<hr>
Calminho
<hr>
<%
        }
    }
%>
</body>
</html>
