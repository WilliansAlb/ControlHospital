<%-- 
    Document   : index.jsp
    Created on : 4/11/2019, 05:04:03 PM
    Author     : yelbetto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Analizador</title>
        <link rel="stylesheet" href="estructura.css">
    </head>
    <body>
        <center>
        <div id="obt" name="obt">
            <form action="Ingreso" method="POST" enctype="multipart/form-data">
                <center>
                    <h1>Analizador Lexico</h1>
                    <input type="file" id="archivo" name="archivo">
                    <br>
                    <button type="submit">Ingresar Archivo</button>
                </center>
            </form>
        </div>
        <div id="txta" name="txta">
            <textarea id="texto1" readonly></textarea>
        </div>
    </center>
    </body>
</html>
