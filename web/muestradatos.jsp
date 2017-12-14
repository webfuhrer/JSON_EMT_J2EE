<%-- 
    Document   : muestradatos
    Created on : 14-dic-2017, 13:00:00
    Author     : luis
--%>

<%@page import="paqueteemt.CreaHTML"%>
<%@page import="java.util.List"%>
<%@page import="paqueteemt.Autobus"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Autobus> lista=(List<Autobus>)request.getAttribute("lista");
    String tabla=CreaHTML.crearTabla(lista);
    %>
    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Autobuses:</h1>
        <%=tabla%>
    </body>
</html>
