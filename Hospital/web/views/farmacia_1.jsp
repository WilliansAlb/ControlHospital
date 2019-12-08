<%-- 
    Document   : farmacia
    Created on : 12/11/2019, 12:43:48 AM
    Author     : yelbetto
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="POJOS.Medicamento"%>
<%@page import="Base.FarmaciaDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Farmacia</title>

        <link rel="shortcut icon" href="../img/favicon.ico" type="image/x-icon"> 
        <link rel="stylesheet" href="../css/farmacia_1.css">
        <script type="text/javascript" src="../js/funs.js"></script>
        <script type="text/javascript" src="../js/jquery.js"></script>
        <script type="text/javascript" src="../js/farmacia_1.js"></script>
    </head>
    <body>

        <%HttpSession s = request.getSession();%>
        <%FarmaciaDTO farmacia = new FarmaciaDTO();%>
        <%ArrayList<Medicamento> medicina = new ArrayList<>();
        medicina = farmacia.nombresMedicamentos();
        %>
        <%if (s.getAttribute("mensaje") != null) {%>
        <div id="fme" name="fme">
            <div id="mensaje" name="mensaje">
                <center>
                    <button onclick="ocultar()">CERRAR MENSAJE</button>
                    <br>
                    <br>
                    <img src="../img/problema.jpg" width="30%" height="30%" alt="Mensaje" title="HOUSTON, WE HAVE A PROBLEM" style="float:left;">
                    <h1><%out.print(s.getAttribute("mensaje"));%></h1>
                </center>
            </div>
        </div>
        <%}%> 
        <%@include file="nav.html"%>
        <%@include file="../htmls/farmacia_1.html" %>
        <%
            if (s.getAttribute("mensaje") != null) {%>
        <script>
            function ocultar() {
                var x = document.getElementById("mensaje");
                var y = document.getElementById("fme");
                if (x.style.display = 'block') {
                    x.style.display = 'none';
                    y.style.display = 'none';
                }
            }
        </script>
        <%}
            s.setAttribute("mensaje", null);%> 
    </body>
</html>
