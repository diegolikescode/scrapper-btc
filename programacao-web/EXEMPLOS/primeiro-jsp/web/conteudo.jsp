<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ include file="cabecalho.jsp"%>
    <form action="recuperaInformacoes.jsp" method="post">
        Nome: <input type="text" name="txtNome" id="idNome"><br>
        Data Nscimento: <input type="date" name="dtNascimento" id="idNascimento"><br>
        <input type="submit" value="Enviar">
    </form>
    <%@ include file="rodape.jsp"%>
</body>
</html>
