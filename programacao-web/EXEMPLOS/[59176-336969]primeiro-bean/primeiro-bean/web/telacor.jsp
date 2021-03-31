<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="pessoa"
             class="br.unisul.aula.Personalidade"
             scope="session">
    <jsp:setProperty name="pessoa" property="nome" param="txtNome" />
    <jsp:setProperty name="pessoa" property="idade" param="nrIdade" />
</jsp:useBean>
<html>
<head>
    <title>Tela de Cor</title>
</head>
<body>
<form action="resultado.jsp" method="post">
    Cor: <input type="text" name="txtCor" id="idCor"><br>
    <input type="submit" value="enviar">
</form>
</body>
</html>
