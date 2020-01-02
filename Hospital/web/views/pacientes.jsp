<%-- 
    Document   : administracion
    Created on : 5/12/2019, 04:29:30 PM
    Author     : yelbetto
--%>

<%@page import="Base.PacientesDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pacientes</title>
        <link rel="shortcut icon" href="../img/favicon.ico" type="image/x-icon"> 
        <link rel="stylesheet" href="../css/generico.css">
        <link rel="stylesheet" href="../css/nav.css">
        <link rel="stylesheet" href="../css/pacientes.css">
        <script type="text/javascript" src="../js/funs.js"></script>
        <script type="text/javascript" src="../js/jquery.js"></script>
        <script type="text/javascript" src="../js/pestañas.js"></script>
        <script type="text/javascript" src="../js/pacientes.js"></script>
    </head>
    <body>
        <%PacientesDTO pac = new PacientesDTO();
          String[] nombres = pac.nombres();
        %>
        <%@include file="nav.html"%>
        <%@include file="../htmls/pacientes.html" %>
    </body>
</html>
