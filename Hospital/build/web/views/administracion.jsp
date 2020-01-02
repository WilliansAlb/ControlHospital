<%-- 
    Document   : administracion
    Created on : 5/12/2019, 04:29:30 PM
    Author     : yelbetto
--%>

<%@page import="POJOS.Areas"%>
<%@page import="POJOS.Empleos"%>
<%@page import="Base.AdministracionDTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Administración</title>
        <link rel="shortcut icon" href="../img/favicon.ico" type="image/x-icon"> 
        <link rel="stylesheet" href="../css/generico.css">
        <link rel="stylesheet" href="../css/nav.css">
        <link rel="stylesheet" href="../css/administracion.css">
        <script type="text/javascript" src="../js/funs.js"></script>
        <script type="text/javascript" src="../js/jquery.js"></script>
        <script type="text/javascript" src="../js/pestañas.js"></script>
        <script type="text/javascript" src="../js/administracion.js"></script>
    </head>
    <body>
        <%
            Date dNow = new Date();
            SimpleDateFormat ft
                    = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = ft.format(dNow);
            AdministracionDTO admin = new AdministracionDTO();
            Empleos[] cuantos = admin.devolviendo();
            Areas[] cuantas = admin.devolviendoAreas();
        %>

        <%@include file="nav.html"%>
        <%@include file="../htmls/administracion.html" %>
    </body>
</html>
