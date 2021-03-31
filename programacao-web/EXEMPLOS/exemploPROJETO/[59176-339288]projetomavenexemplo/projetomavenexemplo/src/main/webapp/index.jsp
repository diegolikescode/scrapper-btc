<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="org.json.simple.parser.JSONParser" %>
<%@ page import="org.json.simple.JSONAware" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>Por favor, informe seu nome:</p>
<form method="post" action="/primeiro">
    Nome: <input type="text" name="nome" ><br>
    <input type="submit" value="Enviar"> <br> <br>
    ${mensagem}
    <br><br>
    ${json}
    <%
        if (request.getAttribute("json")!=null) {
            JSONParser jsonParser = new JSONParser();
            String parse = (String) request.getAttribute("json");
            JSONArray jsonArray =
                    (JSONArray) ((JSONObject) jsonParser.parse(parse)).get("dados");



            out.println("<br><br>");
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObjectParse = new JSONObject();
                jsonObjectParse = (JSONObject) jsonArray.get(i);
                out.println("<b>nome</b>: " + jsonObjectParse.get("nome") + "<br><br><br>");
                out.println("<b>descrição</b>: " + jsonObjectParse.get("descricao") + "<br>");
            }
        }
         %>
</form>
</body>
</html>
